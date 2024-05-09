package log.qushe8r.stockdiscussionforum.newfeedservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedQueryUseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedResponse;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.NewsFeedQueryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class NewsFeedQueryService implements NewsFeedQueryUseCase {

    private final NewsFeedMapper mapper;
    private final NewsFeedQueryPersistencePort queryPort;

    @Transactional(readOnly = true)
    @Override
    public List<NewsFeedResponse> queryNewsFeeds(Long requestingUserId) {
        return queryPort.findByFollowerId(requestingUserId).stream()
                .map(mapper::toNewsFeedResponse)
                .toList();
    }

}
