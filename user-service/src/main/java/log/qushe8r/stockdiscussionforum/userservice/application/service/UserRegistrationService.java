package log.qushe8r.stockdiscussionforum.userservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.*;
import log.qushe8r.stockdiscussionforum.userservice.domain.User;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserRole;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserStatus;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserException;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@Service
@RequiredArgsConstructor
public class UserRegistrationService implements UserRegistrationUseCase {
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryPersistencePort userQueryPersistencePort;
    private final UserRegisterCommandPersistencePort userRegisterCommandPersistencePort;
    private final UserRegistrationVerificationCodeRedisPort redisPort;
    private final ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public void registerUser(UserRegistrationCommand command) {
        String username = command.getUsername();

        userQueryPersistencePort.findByUsername(username)
                .ifPresent(user -> { throw new UserException(UserExceptionCode.USER_ALREADY_EXIST); });

        String encodedPassword = passwordEncoder.encode(command.getPassword());
        UserJpaEntity userJpaEntity = userRegisterCommandPersistencePort.registerUser(command, encodedPassword);

        Long userId = userJpaEntity.getId();
        String verificationCode = UUID.randomUUID().toString().substring(0, 6);

        redisPort.saveVerificationCode(userId, verificationCode);
        publisher.publishEvent(new UserRegistrationVerificationEvent(userId, username, verificationCode));
    }

    @Transactional
    @Override
    public String verifyCode(Long userId, String code) {
        String savedCode = redisPort.getVerificationCode(userId);

        if (savedCode == null) {
            return "인증 코드의 유효 시간은 10분입니다.";
        }
        if (code.equals(savedCode)) {
            User user = userQueryPersistencePort.findById(userId)
                    .map(mapper::toDomainEntity)
                    .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));

            user.modifyUserStatus(UserStatus.ACTIVE);
            user.modifyUserRole(UserRole.ROLE_USER);
            redisPort.removeVerificationCode(userId);

            UserJpaEntity userJpaEntity = mapper.toJpaEntity(user);
            userRegisterCommandPersistencePort.activateUser(userJpaEntity);

            return "이메일이 성공적으로 인증되었습니다.";
        }
        return "인증 코드가 올바르지 않습니다.";
    }

}
