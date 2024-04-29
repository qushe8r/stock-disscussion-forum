package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostUpdateUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostUpdatePersistencePort;
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

    @Transactional
    @Override
    public void updatePost(Long writerId, Long postId, PostUpdateCommand command) {
        Post post = queryPort.findById(postId)
                .map(mapper::toDomainEntity)
                .orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));

        if (!post.getWriter().id().equals(writerId)) {
            throw new PostException(PostExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        String newTitle = command.getTitle();
        String newContent = command.getContent();

        post.modify(newTitle, newContent);
        PostJpaEntity postJpaEntity = mapper.toJpaEntity(post);
        persistencePort.updatePost(postJpaEntity);
    }

}
