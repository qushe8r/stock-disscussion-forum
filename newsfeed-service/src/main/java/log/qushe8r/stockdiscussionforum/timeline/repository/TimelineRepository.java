package log.qushe8r.stockdiscussionforum.timeline.repository;

import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;
import log.qushe8r.stockdiscussionforum.timeline.entity.Timeline;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {

    List<Timeline> findByFollowerId(Long followerId);

    void deleteByFolloweeAndActivityTypeAndActivityIdOrderByCreatedAtDesc(User followee, ActivityType activityType, Long activityId);
}
