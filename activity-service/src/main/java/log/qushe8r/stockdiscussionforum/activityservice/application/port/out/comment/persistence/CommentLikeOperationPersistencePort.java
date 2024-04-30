package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence;

public interface CommentLikeOperationPersistencePort {
    void likePostByRequestingUser(Long requestingUserId, Long commentId);

    void unLikePostByRequestingUser(Long requestingUserId, Long commentId);
}
