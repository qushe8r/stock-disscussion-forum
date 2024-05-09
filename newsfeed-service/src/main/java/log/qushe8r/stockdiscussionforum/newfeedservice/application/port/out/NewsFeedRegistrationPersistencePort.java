package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out;

import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationCommand;

import java.util.List;

public interface NewsFeedRegistrationPersistencePort {
    void registerNewsFeed(List<Long> followerIds, NewsFeedRegistrationCommand command);
}
