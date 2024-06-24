package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class PostRegistrationService implements PostRegistrationUseCase {

	private final PostRegistrationPersistencePort persistencePort;
	private final NewsFeedServiceClient client;

	@Transactional
	@Override
	public void registerPost(Long requestingUserId, PostRegistrationCommand command) {
		Long postId = persistencePort.registerPost(requestingUserId, command);
		client.registerNewsfeeds(NewsFeedCommand.postRegister(requestingUserId, postId));
	}

}
