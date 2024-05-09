package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "post_like")
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaEntity postJpaEntity;

    public PostLikeJpaEntity(Long userId, PostJpaEntity postJpaEntity) {
        this.userId = userId;
        this.postJpaEntity = postJpaEntity;
    }

}
