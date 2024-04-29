package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.CommentResponse;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Writer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommentMapper {

    public CommentJpaEntity toJpaEntity(Comment comment) {
        return new CommentJpaEntity(
                comment.getId(),
                comment.getContent(),
                comment.getWriter().id()
        );
    }

    public List<CommentJpaEntity> toJpaEntities(List<Comment> comments) {
        return comments.stream()
                .map(this::toJpaEntity)
                .toList();
    }

    public Comment toDomainEntity(CommentJpaEntity commentJpaEntity, Map<Long, String> userIdNickname) {
        Long writerId = commentJpaEntity.getWriterId();

        return Comment.create(
                commentJpaEntity.getId(),
                new Writer(writerId, userIdNickname.get(writerId)),
                commentJpaEntity.getContent()
        );
    }

    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getWriter().name(),
                comment.getContent()
        );
    }

    public List<CommentResponse> toResponses(List<Comment> comments) {
        return comments.stream()
                .map(this::toResponse)
                .toList();
    }
}
