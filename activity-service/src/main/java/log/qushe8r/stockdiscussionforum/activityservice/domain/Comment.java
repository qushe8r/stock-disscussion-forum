package log.qushe8r.stockdiscussionforum.activityservice.domain;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentExceptionCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private final Long id;
    private final Writer writer;
    private final Post post;
    private String content;

    public static Comment create(Long id, Writer writer, Post post, String content) {
        return new Comment(id, writer, post, content);
    }

    public void update(Long userId, CommentUpdateCommand command) {
        if (!this.writer.id().equals(userId)) {
            throw new CommentException(CommentExceptionCode.CANNOT_CHANGE_INFORMATION);
        }
        if (command.getContent() != null) {
            this.content = command.getContent();
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                '}';
    }

    // 자신의 댓글에는 좋아요를 할 수 없다.

}
