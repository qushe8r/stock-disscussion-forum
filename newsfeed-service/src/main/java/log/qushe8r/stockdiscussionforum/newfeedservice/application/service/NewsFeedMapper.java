package log.qushe8r.stockdiscussionforum.newfeedservice.application.service;

import log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence.NewsFeedJpaEntity;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationCommand;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsFeedMapper {

    public NewsFeedJpaEntity toJpaEntity(Long followerId, NewsFeedRegistrationCommand command) {
        return new NewsFeedJpaEntity(
                command.followeeId(),
                followerId,
                command.activityType(),
                command.activityId(),
                command.associatedId());
    }

    public NewsFeedResponse toNewsFeedResponse(NewsFeedJpaEntity newsFeedJpaEntity) {
        return new NewsFeedResponse(
         newsFeedJpaEntity.getId(),
         newsFeedJpaEntity.getFolloweeId(),
         newsFeedJpaEntity.getFollowerId(),
         newsFeedJpaEntity.getActivityType(),
         newsFeedJpaEntity.getActivityId(),
         newsFeedJpaEntity.getAssociatedId()
        );
    }
}
