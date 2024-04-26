package log.qushe8r.stockdiscussionforum.activityservice.application.port.in;

public interface PostDeletionUseCase {

    void deletePost(Long userId, Long postId);

}
