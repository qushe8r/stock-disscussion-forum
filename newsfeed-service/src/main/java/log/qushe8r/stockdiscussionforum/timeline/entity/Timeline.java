package log.qushe8r.stockdiscussionforum.timeline.entity;

import jakarta.persistence.*;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Timeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "followee_id")
    private User followee;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    private Long activityId;

    @CreatedDate
    private LocalDateTime createdAt;

    public Timeline(User followee, User follower, ActivityType activityType, Long activityId) {
        this.followee = followee;
        this.follower = follower;
        this.activityType = activityType;
        this.activityId = activityId;
    }
}
