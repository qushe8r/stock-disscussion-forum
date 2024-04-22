package log.qushe8r.stockdiscussionforum.user.service;

import log.qushe8r.stockdiscussionforum.mail.EmailService;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.user.dto.*;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import log.qushe8r.stockdiscussionforum.user.entity.UserRole;
import log.qushe8r.stockdiscussionforum.user.entity.UserStatus;
import log.qushe8r.stockdiscussionforum.user.exception.UserException;
import log.qushe8r.stockdiscussionforum.user.exception.UserExceptionCode;
import log.qushe8r.stockdiscussionforum.user.mapper.UserMapper;
import log.qushe8r.stockdiscussionforum.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserCreateRequest request) {
        userRepository.findByUsername(request.username())
                .ifPresent(user -> {
                    throw new UserException(UserExceptionCode.USER_ALREADY_EXIST);
                });

        String encodedPassword = passwordEncoder.encode(request.password());
        User user = userMapper.toEntity(request, encodedPassword);
        User savedUser = userRepository.save(user);

        String verificationCode = getVerificationCode();
        verificationCodeService.saveVerificationCode(savedUser.getId(), verificationCode);
        emailService.sendVerificationEmail(user.getUsername(), savedUser.getId(), verificationCode);
    }


    @Transactional
    public void modifyInformation(Long userId, UserModifyRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
        user.modifyInformation(request.nickname(), request.bio());
    }

    public UserDetailsResponse findById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDetailsResponse)
                .orElseThrow();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
    }

    public List<UserResponse> findAll() {
        // TODO: 페이징 처리 ? 유저 리스트가 필요하긴 함...?
        return userRepository.findAll()
                .stream().map(userMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public String verifyCode(Long userId, String code) {
        String savedCode = verificationCodeService.getVerificationCode(userId);
        if (savedCode == null) {
            return "인증 코드의 유효 시간은 10분입니다.";
        }
        if (code.equals(savedCode)) {
            modifyUserStatus(userId, UserStatus.ACTIVE);
            modifyUserRole(userId, UserRole.ROLE_USER);
            verificationCodeService.removeVerificationCode(userId);
            return "이메일이 성공적으로 인증되었습니다.";
        }
        return "인증 코드가 올바르지 않습니다.";
    }

    private String getVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }


    private void modifyUserStatus(Long userId, UserStatus userStatus) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
        user.modifyStatus(userStatus);
        userRepository.save(user);
    }

    private void modifyUserRole(Long userId, UserRole userRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
        user.modifyRole(userRole);
        userRepository.save(user);
    }

    @Transactional
    public void modifyPassword(Long userId, AuthenticatedUser authenticatedUser, PasswordModifyRequest request) {
        if (!Objects.equals(authenticatedUser.getUserId(), userId)) {
            throw new UserException(UserExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new UserException(UserExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        String encodedNewPassword = passwordEncoder.encode(request.newPassword());
        user.modifyPassword(encodedNewPassword);
    }

}
