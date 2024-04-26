package log.qushe8r.stockdiscussionforum.activityservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private final Long id;
    private final Writer writer;
    private String content;

    public static Comment create(Long id, Writer writer, String content) {
        return new Comment(id, writer, content);
    }

    public void modify(String content) {
        if (content != null) {
            this.content = content;
        }
    }

    // 자신의 댓글에는 좋아요를 할 수 없다.

}
