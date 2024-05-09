package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment;

public interface CommentLikeOperationUseCase {

    boolean operateCommentLike(Long requestingUserId, Long commentId);

}
