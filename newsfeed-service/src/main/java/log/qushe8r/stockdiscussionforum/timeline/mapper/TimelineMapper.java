package log.qushe8r.stockdiscussionforum.timeline.mapper;

import log.qushe8r.stockdiscussionforum.timeline.dto.TimelineResponse;
import log.qushe8r.stockdiscussionforum.timeline.entity.Timeline;
import org.springframework.stereotype.Component;

@Component
public class TimelineMapper {
    public TimelineResponse toResponse(Timeline timeline) {
        return new TimelineResponse(
                timeline.getFollowee().getNickname(),
                timeline.getActivityType(),
                timeline.getActivityId(),
                timeline.getCreatedAt()
        );
    }

}
