package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;

import java.util.List;
import java.util.Optional;

public interface PostQueryPersistencePort {

    Optional<PostJpaEntity> findById(Long postId);

    Optional<PostJpaEntity> findWithPostLike(Long postId);

    List<PostJpaEntity> findByWriterId(Long writerId);

}
