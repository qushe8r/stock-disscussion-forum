package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationCommand;

public interface CommentRegistrationPersistencePort {

    void registerComment(Long userId, Long postId, CommentRegistrationCommand command);

}
