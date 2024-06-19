package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence;

import java.util.Optional;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;

public interface CommentQueryPersistencePort {

    Optional<CommentJpaEntity> findById(Long commentId);

}
