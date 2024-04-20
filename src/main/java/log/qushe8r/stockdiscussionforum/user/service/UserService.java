package log.qushe8r.stockdiscussionforum.user.service;

import log.qushe8r.stockdiscussionforum.mail.EmailService;
import log.qushe8r.stockdiscussionforum.user.dto.UserCreateRequest;
import log.qushe8r.stockdiscussionforum.user.dto.UserDetailsResponse;
import log.qushe8r.stockdiscussionforum.user.dto.UserModifyRequest;
import log.qushe8r.stockdiscussionforum.user.dto.UserResponse;
import log.qushe8r.stockdiscussionforum.user.entity.UserRole;
import log.qushe8r.stockdiscussionforum.user.entity.User;
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
import java.util.UUID;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(UserCreateRequest request) {
//        String encodedPassword = passwordEncoder.encode(request.password());
        User user = userMapper.toEntity(request, request.password());
        User savedUser = userRepository.save(user);

        String verificationCode = getVerificationCode();
        verificationCodeService.saveVerificationCode(savedUser.getId(), verificationCode);
        emailService.sendVerificationEmail(user.getUsername(), savedUser.getId(), verificationCode);
    }


    @Transactional
    public void modify(Long userId, UserModifyRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
        user.modify(request.nickname(), request.bio());
    }

    public UserDetailsResponse get(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDetailsResponse)
                .orElseThrow();
    }

    public List<UserResponse> getAll() {
        // TODO: 페이징 처리 ? 유저 리스트가 필요하긴 함...?
        return userRepository.findAll()
                .stream().map(userMapper::toResponse)
                .toList();
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public String verifyCode(Long userId, String code) {
        String savedCode = verificationCodeService.getVerificationCode(userId);

        if (code.equals(savedCode)) {
            updateUserStatus(userId, UserStatus.ACTIVE);
            updateUserRole(userId, UserRole.ROLE_USER);
            return "이메일이 성공적으로 인증되었습니다.";
        }
        return "인증 코드가 올바르지 않습니다.";
    }

    private String getVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 7);
    }


    private void updateUserStatus(Long userId, UserStatus userStatus) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
        user.updateStatus(userStatus);
        userRepository.save(user);
    }

    private void updateUserRole(Long userId, UserRole userRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));
        user.updateRole(userRole);
        userRepository.save(user);
    }

}
