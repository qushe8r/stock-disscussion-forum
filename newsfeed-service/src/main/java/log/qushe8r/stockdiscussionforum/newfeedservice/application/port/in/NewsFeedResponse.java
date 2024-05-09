package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in;

import log.qushe8r.stockdiscussionforum.newfeedservice.domain.ActivityType;

public record NewsFeedResponse(
        Long id, Long followeeId, Long followerId, ActivityType activityType, Long activityId, Long associatedId) {

}
