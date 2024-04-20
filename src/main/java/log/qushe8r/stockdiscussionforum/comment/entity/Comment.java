package log.qushe8r.stockdiscussionforum.comment.entity;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.post.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public Comment(Long id) {
        this.id = id;
    }

    public void modify(String content) {
        this.content = content;
    }
}
