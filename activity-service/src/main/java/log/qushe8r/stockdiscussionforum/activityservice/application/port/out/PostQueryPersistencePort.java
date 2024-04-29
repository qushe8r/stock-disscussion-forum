package log.qushe8r.stockdiscussionforum.activityservice.application.port.out;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence.PostJpaEntity;

import java.util.List;
import java.util.Optional;

public interface PostQueryPersistencePort {

    Optional<PostJpaEntity> findById(Long postId);

    List<PostJpaEntity> findByWriterId(Long writerId);

}
