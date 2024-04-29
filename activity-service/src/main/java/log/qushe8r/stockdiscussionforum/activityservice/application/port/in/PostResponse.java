package log.qushe8r.stockdiscussionforum.activityservice.application.port.in;

public record PostResponse(Long postId, String title, String content, String writer) {
}
