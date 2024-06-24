package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CommentRegistrationService implements CommentRegistrationUseCase {

    private final CommentRegistrationPersistencePort persistencePort;
    private final NewsFeedServiceClient client;

    @Override
    public void registerComment(Long requestingUserId, Long postId, CommentRegistrationCommand command) {
        Long commentId = persistencePort.registerComment(requestingUserId, postId, command);
        client.registerNewsfeeds(NewsFeedCommand.registerCommand(requestingUserId, commentId, postId));
    }

}
