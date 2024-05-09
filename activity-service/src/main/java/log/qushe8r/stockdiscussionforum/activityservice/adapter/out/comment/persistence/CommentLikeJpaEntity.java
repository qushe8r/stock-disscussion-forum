package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comment_likes")
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentJpaEntity commentJpaEntity;

    public CommentLikeJpaEntity(Long writerId, CommentJpaEntity commentJpaEntity) {
        this.writerId = writerId;
        this.commentJpaEntity = commentJpaEntity;
    }

}
