package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in;

import java.util.List;

public interface NewsFeedQueryUseCase {

    List<NewsFeedResponse> queryNewsFeeds(Long requestingUserId);

}
