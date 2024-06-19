package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "as_post_likes")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private PostJpaEntity postJpaEntity;

    public PostLikeJpaEntity(Long userId, PostJpaEntity postJpaEntity) {
        this.userId = userId;
        this.postJpaEntity = postJpaEntity;
    }

}
