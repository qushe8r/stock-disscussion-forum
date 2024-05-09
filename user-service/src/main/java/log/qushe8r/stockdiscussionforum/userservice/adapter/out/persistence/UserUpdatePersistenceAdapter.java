package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserUpdateCommandPersistencePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserUpdatePersistenceAdapter implements UserUpdateCommandPersistencePort {

    private final UserJpaRepository repository;


    @Override
    public void update(UserJpaEntity jpaEntity) {
        repository.save(jpaEntity);
    }

}
