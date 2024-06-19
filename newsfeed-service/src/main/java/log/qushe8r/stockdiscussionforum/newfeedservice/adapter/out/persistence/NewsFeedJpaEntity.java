package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.newfeedservice.domain.ActivityType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "nfs_news_feeds")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NewsFeedJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_feed_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "followee_id", nullable = false, updatable = false)
    private Long followeeId;

    @Column(name = "follower_id", nullable = false, updatable = false)
    private Long followerId;

    @Column(name = "activity_type", nullable = false, updatable = false, length = 12)
    private ActivityType activityType;

    @Column(name = "activity_id", nullable = false, updatable = false)
    private Long activityId;

    @Column(name = "associate_id", updatable = false)
    private Long associatedId; // ActivityType.Comment 일 경우 postId

    public NewsFeedJpaEntity(Long followeeId, Long followerId, ActivityType activityType,Long activityId, Long associatedId) {
        this.followeeId = followeeId;
        this.followerId = followerId;
        this.activityType = activityType;
        this.activityId = activityId;
        this.associatedId = associatedId;
    }

}
