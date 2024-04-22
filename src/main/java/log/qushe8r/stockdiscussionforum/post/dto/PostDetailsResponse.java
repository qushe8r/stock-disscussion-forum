package log.qushe8r.stockdiscussionforum.post.dto;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentResponse;

import java.util.List;

public record PostDetailsResponse(Long postId, String title, String content, List<CommentResponse> comments) {

}
