package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment;

public interface CommentRegistrationUseCase {
    void registerComment(Long userId, Long postId, CommentRegistrationCommand command);
}
