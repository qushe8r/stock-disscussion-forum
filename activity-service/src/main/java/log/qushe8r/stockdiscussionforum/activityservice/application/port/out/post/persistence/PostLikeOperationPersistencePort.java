package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence;

public interface PostLikeOperationPersistencePort {

    void likePostByRequestingUser(Long requestingUserId, Long postId);

    void unLikePostByRequestingUser(Long requestingUserId, Long postId);

}
