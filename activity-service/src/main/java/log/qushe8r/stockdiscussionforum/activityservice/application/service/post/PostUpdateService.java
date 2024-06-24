package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostUpdateUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostUpdatePersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class PostUpdateService implements PostUpdateUseCase {

	private final PostMapper mapper;
	private final PostQueryPersistencePort queryPort;
	private final PostUpdatePersistencePort persistencePort;
	private final NewsFeedServiceClient client;

	@Transactional
	@Override
	public void updatePost(Long requestingUserId, Long postId, PostUpdateCommand command) {
		String newTitle = command.getTitle();
		String newContent = command.getContent();

		Post post = queryPort.findById(postId)
			.map(mapper::toDomainEntityWriterNicknameNull)
			.orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));

		post.update(requestingUserId, newTitle, newContent, this::updateExecute);
		client.registerNewsfeeds(NewsFeedCommand.postUpdate(requestingUserId, post.getId()));
	}

	private void updateExecute(Post post) {
		persistencePort.updatePost(mapper.toJpaEntity(post));
	}

}
