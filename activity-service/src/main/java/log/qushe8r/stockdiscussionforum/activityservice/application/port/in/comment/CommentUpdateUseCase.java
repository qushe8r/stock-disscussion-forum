package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment;

public interface CommentUpdateUseCase {

    void updateComment(Long userId, Long commentId, CommentUpdateCommand command);

}
