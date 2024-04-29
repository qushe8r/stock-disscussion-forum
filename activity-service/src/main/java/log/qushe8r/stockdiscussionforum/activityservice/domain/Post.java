package log.qushe8r.stockdiscussionforum.activityservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {

    private final Long id;
    private String title;
    private String content;
    private final Writer writer;
    private List<Comment> comments;

    public static Post create(Long id, String title, String content, Writer writer, List<Comment> comments) {
        return new Post(
                id,
                title,
                content,
                writer,
                comments == null ? new ArrayList<>() : comments
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

    // 자신의 글에는 좋아요를 할 수 없다.
}
