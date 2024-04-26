package log.qushe8r.stockdiscussionforum.activityservice.application.port.out;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.PostJpaEntity;

import java.util.Optional;

public interface PostQueryPersistencePort {

    Optional<PostJpaEntity> findById(Long postId);

}
