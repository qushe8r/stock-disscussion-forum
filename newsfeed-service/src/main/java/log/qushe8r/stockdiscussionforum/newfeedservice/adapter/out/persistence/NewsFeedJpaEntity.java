package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.newfeedservice.domain.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "newsfeeds")
@NoArgsConstructor
@AllArgsConstructor
public class NewsFeedJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followeeId;
    private Long followerId;

    private ActivityType activityType;
    private Long activityId;
    private Long associatedId; // activityType이 댓글일 경우 PostId

    public NewsFeedJpaEntity(Long followeeId, Long followerId, ActivityType activityType,Long activityId, Long associatedId) {
        this.followeeId = followeeId;
        this.followerId = followerId;
        this.activityType = activityType;
        this.activityId = activityId;
        this.associatedId = associatedId;
    }

}
