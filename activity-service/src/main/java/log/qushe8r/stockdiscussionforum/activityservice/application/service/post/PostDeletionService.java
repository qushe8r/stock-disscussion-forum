package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostDeletionUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostDeletionPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class PostDeletionService implements PostDeletionUseCase {

    private final PostQueryPersistencePort queryPort;
    private final PostDeletionPersistencePort persistencePort;

    @Transactional
    @Override
    public void deletePost(Long userId, Long postId) {
        Optional<PostJpaEntity> optionalPostJpaEntity = queryPort.findById(postId);

        if (optionalPostJpaEntity.isEmpty()) {
            return;
        }

        PostJpaEntity postJpaEntity = optionalPostJpaEntity.get();
        Long writerId = postJpaEntity.getWriterId();

        if (!userId.equals(writerId)) {
            throw new PostException(PostExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        persistencePort.deleteById(postId);
    }

}
