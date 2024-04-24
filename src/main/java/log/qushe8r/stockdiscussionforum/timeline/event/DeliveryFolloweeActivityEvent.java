package log.qushe8r.stockdiscussionforum.timeline.event;

import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;

public record DeliveryFolloweeActivityEvent(Long followeeId, ActivityType activityType, Long activityId) {
}
