package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.FollowOperationPersistencePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FollowOperationPersistenceAdapter implements FollowOperationPersistencePort {

    private final FollowJpaRepository repository;

    @Override
    public void follow(Long followerId, Long followeeId) {
        FollowJpaEntity followJpaEntity = new FollowJpaEntity(followerId, followeeId);
        repository.save(followJpaEntity);
    }

    @Override
    public void unfollow(Long followerId, Long followeeId) {
        repository.deleteByFollowerIdAndFolloweeId(followerId, followeeId);
    }

}
