package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "nfs_follows")
@NoArgsConstructor
@AllArgsConstructor
public class FollowJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "follower_id", nullable = false, updatable = false)
    private Long followerId;

    @Column(name = "followee_id", nullable = false, updatable = false)
    private Long followeeId;

    public FollowJpaEntity(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }
}
