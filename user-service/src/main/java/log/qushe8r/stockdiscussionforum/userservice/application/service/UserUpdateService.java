package log.qushe8r.stockdiscussionforum.userservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateUseCase;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserUpdateCommandPersistencePort;
import log.qushe8r.stockdiscussionforum.userservice.domain.User;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserException;
import log.qushe8r.stockdiscussionforum.userservice.domain.exception.UserExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UserUpdateService implements UserUpdateUseCase {

    private final UserMapper mapper;
    private final UserQueryPersistencePort queryPort;
    private final UserUpdateCommandPersistencePort persistencePort;

    @Transactional
    @Override
    public void userUpdateInformation(Long userId, UserUpdateCommand command) {
        User user = queryPort.findById(userId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUND));

        String newNickname = command.getNickname();
        String newBio = command.getBio();
        String newProfileImageUrl = command.getProfileImageUrl();
        user.modifyUserInformation(newNickname, newBio, newProfileImageUrl);
        UserJpaEntity userJpaEntity = mapper.toJpaEntity(user);

        persistencePort.update(userJpaEntity);
    }

}
