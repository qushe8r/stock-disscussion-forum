package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserRegisterCommandPersistencePort;
import log.qushe8r.stockdiscussionforum.userservice.application.service.UserMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserRegisterCommandPersistenceAdapter implements UserRegisterCommandPersistencePort {

    private final UserMapper mapper;
    private final UserJpaRepository repository;

    @Override
    public UserJpaEntity registerUser(UserRegistrationCommand command, String encodedPassword) {
        UserJpaEntity userJpaEntity = mapper.toJpaEntity(command, encodedPassword);
        return repository.save(userJpaEntity);
    }

    @Override
    public void activateUser(UserJpaEntity userJpaEntity) {
        repository.save(userJpaEntity);
    }

}
