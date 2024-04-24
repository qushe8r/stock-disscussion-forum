package log.qushe8r.stockdiscussionforum.timeline.service;

import log.qushe8r.stockdiscussionforum.follower.entity.Follow;
import log.qushe8r.stockdiscussionforum.follower.repository.FollowRepository;
import log.qushe8r.stockdiscussionforum.timeline.dto.TimelineResponse;
import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;
import log.qushe8r.stockdiscussionforum.timeline.entity.Timeline;
import log.qushe8r.stockdiscussionforum.timeline.event.DeliveryFolloweeActivityEvent;
import log.qushe8r.stockdiscussionforum.timeline.event.RemoveFolloweeActivityEvent;
import log.qushe8r.stockdiscussionforum.timeline.mapper.TimelineMapper;
import log.qushe8r.stockdiscussionforum.timeline.repository.TimelineRepository;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimelineService {
    private final TimelineMapper timelineMapper;
    private final TimelineRepository timelineRepository;
    private final FollowRepository followRepository;

    @EventListener
    @Transactional
    public void deliveryTimeline(DeliveryFolloweeActivityEvent event) {
        Long followeeId = event.followeeId();
        User followee = new User(followeeId);
        ActivityType activityType = event.activityType();
        Long activityId = event.activityId();

        List<Timeline> timelines = followRepository.findByFolloweeId(followeeId).stream()
                .map(Follow::getFollower)
                .map(follower -> new Timeline(followee, follower, activityType, activityId))
                .toList();

        //TODO: bulk insert 로 변경해야함
        timelineRepository.saveAll(timelines);
    }

    @EventListener
    @Transactional
    public void removeTimeline(RemoveFolloweeActivityEvent event) {
        Long followeeId = event.followeeId();
        User followee = new User(followeeId);
        ActivityType activityType = event.activityType();
        Long activityId = event.activityId();

        //TODO: 쿼리 확인 해야함
        timelineRepository.deleteByFolloweeAndActivityTypeAndActivityIdOrderByCreatedAtDesc(followee, activityType, activityId);
    }

    public List<TimelineResponse> findByFollowerId(Long userId) {
        return timelineRepository.findByFollowerId(userId)
                .stream().map(timelineMapper::toResponse)
                .toList();
    }
}
