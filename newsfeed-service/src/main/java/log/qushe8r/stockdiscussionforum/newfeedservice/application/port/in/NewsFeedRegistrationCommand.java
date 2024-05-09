package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in;

import log.qushe8r.stockdiscussionforum.newfeedservice.domain.ActivityType;

public record NewsFeedRegistrationCommand(Long followeeId, ActivityType activityType, Long activityId, Long associatedId) {
}
