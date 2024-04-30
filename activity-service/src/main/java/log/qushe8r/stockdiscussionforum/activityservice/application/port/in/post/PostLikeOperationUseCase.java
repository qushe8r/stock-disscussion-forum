package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post;

public interface PostLikeOperationUseCase {

    boolean operatePostLike(Long requestingUserId, Long postId);

}
