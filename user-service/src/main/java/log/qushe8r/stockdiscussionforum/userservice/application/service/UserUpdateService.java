package log.qushe8r.stockdiscussionforum.userservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserPasswordUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateUseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserUpdateCommandPersistencePort;
import log.qushe8r.stockdiscussionforum.userservice.domain.User;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserException;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UserUpdateService implements UserUpdateUseCase {

    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryPersistencePort queryPort;
    private final UserUpdateCommandPersistencePort updatePersistencePort;

    @Transactional
    @Override
    public void updateUserInformation(Long userId, UserUpdateCommand command) {
        User user = queryPort.findById(userId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));

        String newNickname = command.getNickname();
        String newBio = command.getBio();
        String newProfileImageUrl = command.getProfileImageUrl();
        user.modifyUserInformation(newNickname, newBio, newProfileImageUrl);
        UserJpaEntity userJpaEntity = mapper.toJpaEntity(user);

        updatePersistencePort.update(userJpaEntity);
    }

    @Transactional
    @Override
    public void updateUserPassword(Long userId, UserPasswordUpdateCommand command) {
        User user = queryPort.findById(userId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));

        String oldPassword = command.getOldPassword();
        String newPassword = command.getNewPassword();

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new UserException(UserExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.modifyUserPassword(encodedNewPassword);
        UserJpaEntity userJpaEntity = mapper.toJpaEntity(user);

        updatePersistencePort.update(userJpaEntity);
    }

}
