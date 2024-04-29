package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaEntity postJpaEntity;

    private String content;
    private Long writerId;

    public CommentJpaEntity(Long postId, String content, Long writerId) {
        this.postJpaEntity = new PostJpaEntity(postId);
        this.content = content;
        this.writerId = writerId;
    }

}
