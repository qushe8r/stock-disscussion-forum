package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post;

public record PostResponse(Long postId, String title, String content, String writer) {
}
