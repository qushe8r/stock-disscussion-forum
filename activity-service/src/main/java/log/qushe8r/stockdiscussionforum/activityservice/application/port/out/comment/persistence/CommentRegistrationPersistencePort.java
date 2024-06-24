package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationCommand;

public interface CommentRegistrationPersistencePort {

    Long registerComment(Long userId, Long postId, CommentRegistrationCommand command);

}
