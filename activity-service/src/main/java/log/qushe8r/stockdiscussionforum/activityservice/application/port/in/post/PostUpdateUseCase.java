package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post;

public interface PostUpdateUseCase {

    void updatePost(Long userId, Long postId, PostUpdateCommand command);

}
