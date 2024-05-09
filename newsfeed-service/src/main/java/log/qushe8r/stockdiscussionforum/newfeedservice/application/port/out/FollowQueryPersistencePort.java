package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out;

import log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence.FollowJpaEntity;

import java.util.List;
import java.util.Optional;

public interface FollowQueryPersistencePort {

    Optional<FollowJpaEntity> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    List<FollowJpaEntity> findByFolloweeId(Long followeeId);

}
