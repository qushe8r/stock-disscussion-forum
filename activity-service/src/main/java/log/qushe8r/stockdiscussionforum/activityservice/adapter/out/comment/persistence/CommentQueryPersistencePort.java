package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import java.util.Optional;

public interface CommentQueryPersistencePort {

    Optional<CommentJpaEntity> findById(Long commentId);

}
