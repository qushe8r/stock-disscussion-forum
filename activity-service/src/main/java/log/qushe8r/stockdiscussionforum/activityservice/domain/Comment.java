package log.qushe8r.stockdiscussionforum.activityservice.domain;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private final Long id;
    private final Writer writer;
    private final Post post;
    private String content;
    private CommentLike commentLike;

    public static Comment create(Long id, Writer writer, Post post, String content, CommentLike commentLike) {
        return new Comment(id, writer, post, content, commentLike);
    }

    public void update(Long userId, CommentUpdateCommand command) {
        if (!this.writer.id().equals(userId)) {
            throw new CommentException(CommentExceptionCode.CANNOT_CHANGE_INFORMATION);
        }
        if (command.getContent() != null) {
            this.content = command.getContent();
        }
    }

    public void delete(Long requestingUserId, Consumer<Long> deleteAction) {
        if (this.writer.id().equals(requestingUserId)) {
            throw new CommentException(CommentExceptionCode.CANNOT_CHANGE_INFORMATION);
        }
        deleteAction.accept(this.id);
    }

    // 자신의 댓글에는 좋아요를 할 수 없다.
    public boolean toggleCommentLikeForRequestingUser(Long requestingUserId,
													  BiConsumer<Long, Long> likePostFunction,
													  BiConsumer<Long, Long> unlikePostFunction) {
        if (confirmOtherUser(requestingUserId)) {
            throw new PostException(PostExceptionCode.CANNOT_POST_LIKE_SELF);
        }
        if (this.commentLike.likeByRequestingUser()) {
            // 좋아요 버튼 누르기
            unlikePostFunction.accept(requestingUserId, this.id);
            return false;
        }
        // 좋아요 버튼 취소하기
        likePostFunction.accept(requestingUserId, this.id);
        return true;
    }

    public boolean confirmOtherUser(Long requestingUserId) {
        assert this.writer != null;
        return requestingUserId.equals(this.writer.id());
    }

}
