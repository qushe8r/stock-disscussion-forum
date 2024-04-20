package log.qushe8r.stockdiscussionforum.comment.mapper;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentCreateRequest;
import log.qushe8r.stockdiscussionforum.comment.dto.CommentResponse;
import log.qushe8r.stockdiscussionforum.comment.entity.Comment;
import log.qushe8r.stockdiscussionforum.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(Long postId, CommentCreateRequest request) {
        return new Comment(request.contents(), new Post(postId));
    }

    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getContent());
    }
}
