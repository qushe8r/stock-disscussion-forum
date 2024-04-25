package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserDeletionCommandPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserDeletionPersistenceAdapter implements UserDeletionCommandPort {

    private final UserJpaRepository repository;


    @Override
    public void deleteById(Long userId) {
        repository.deleteById(userId);
    }

}
