package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostDeletionUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostDeletionPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class PostDeletionService implements PostDeletionUseCase {

    private final PostMapper mapper;
    private final PostQueryPersistencePort queryPort;
    private final PostDeletionPersistencePort persistencePort;

    @Transactional
    @Override
    public void deletePost(Long userId, Long postId) {
        Optional<Post> optionalPost = queryPort.findById(postId)
                .map(mapper::toDomainEntity);

        if (optionalPost.isEmpty()) {
            return;
        }

        Post post = optionalPost.get();
        Long writerId = post.getWriter().id();

        if (!userId.equals(writerId)) {
            throw new PostException(PostExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        persistencePort.deleteById(postId);
    }

}
