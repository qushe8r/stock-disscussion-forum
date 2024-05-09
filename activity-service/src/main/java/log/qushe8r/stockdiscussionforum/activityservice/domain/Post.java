package log.qushe8r.stockdiscussionforum.activityservice.domain;

import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {

    private final Long id;
    private String title;
    private String content;
    private final Writer writer;
    private List<Comment> comments;
    private PostLike postLike;

    public static Post create(Long id, String title, String content, Writer writer, List<Comment> comments, PostLike postLike) {
        return new Post(
                id,
                title,
                content,
                writer,
                comments == null ? new ArrayList<>() : comments,
                postLike
        );
    }

    public static Post createWithIdOnly(Long postId) {
        return new Post(postId);
    }

    private Post(Long id) {
        this.id = id;
        this.writer = null;
    }

    public void modify(String title, String content) {
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
    }

    public void update(Long requestingUserId, String title, String content, Consumer<Post> updateFunction) {
        if (confirmOtherUser(requestingUserId)) {
            throw new PostException(PostExceptionCode.CANNOT_CHANGE_INFORMATION);
        }
        this.modify(title, content);
        updateFunction.accept(this);
    }

    public void delete(Long requestingUserId, LongConsumer deleteFunction) {
        if (confirmOtherUser(requestingUserId)) {
            throw new PostException(PostExceptionCode.CANNOT_CHANGE_INFORMATION);
        }
        deleteFunction.accept(this.id);
    }

    public boolean togglePostLikeForRequestingUser(Long requestingUserId,
                                                   BiConsumer<Long, Long> likePostFunction,
                                                   BiConsumer<Long, Long> unlikePostFunction) {
        if (confirmOtherUser(requestingUserId)) {
            throw new PostException(PostExceptionCode.CANNOT_POST_LIKE_SELF);
        }
        if (this.postLike.likeByMe()) {
            likePostFunction.accept(requestingUserId, this.id);
            return true;
        }
        unlikePostFunction.accept(requestingUserId, this.id);
        return false;
    }

    public boolean confirmOtherUser(Long requestingUserId) {
        assert this.writer != null;
        return !requestingUserId.equals(this.writer.id());
    }

}
