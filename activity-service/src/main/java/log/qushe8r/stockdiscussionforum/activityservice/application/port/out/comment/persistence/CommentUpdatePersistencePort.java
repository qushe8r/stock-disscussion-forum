package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;

public interface CommentUpdatePersistencePort {

    void updateComment(CommentJpaEntity commentJpaEntity);

}
