package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserQueryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserQueryPersistenceAdapter implements UserQueryPersistencePort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserJpaEntity> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<UserJpaEntity> findById(Long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public List<UserJpaEntity> findByIds(List<Long> userIds) {
        return userJpaRepository.findByIds(userIds);
    }

}
