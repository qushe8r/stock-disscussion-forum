package log.qushe8r.stockdiscussionforum.comment.entity;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;


    public CommentLike(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public CommentLike(Long userId, Long commentId) {
        this.user = new User(userId);
        this.comment = new Comment(commentId);
    }
}
