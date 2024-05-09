package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post;

import java.util.List;

public record PostDetailsResponse(Long postId, String title, String content, String writer, List<CommentResponse> comments) {
}
