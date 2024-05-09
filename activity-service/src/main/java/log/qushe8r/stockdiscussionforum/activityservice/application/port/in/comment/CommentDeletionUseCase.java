package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment;

public interface CommentDeletionUseCase {

    void deleteComment(Long userId, Long commentId);

}
