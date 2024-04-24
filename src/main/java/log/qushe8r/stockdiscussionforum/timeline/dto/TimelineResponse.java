package log.qushe8r.stockdiscussionforum.timeline.dto;

import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;

import java.time.LocalDateTime;

public record TimelineResponse(String nickname, ActivityType activityType, Long activityId, LocalDateTime createdAt) {
}
