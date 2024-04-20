package log.qushe8r.stockdiscussionforum.post.entity;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostLike(Long userId, Long postId) {
        this.user = new User(userId);
        this.post = new Post(postId);
    }

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}

