package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.FollowQueryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RequiredArgsConstructor
public class FollowQueryPersistenceAdapter implements FollowQueryPersistencePort {

    private final FollowJpaRepository repository;

    @Override
    public Optional<FollowJpaEntity> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId) {
        return repository.findByFollowerIdAndFolloweeId(followerId, followeeId);
    }

    @Override
    public List<FollowJpaEntity> findByFolloweeId(Long followeeId) {
        return repository.findByFolloweeId(followeeId);
    }

}
