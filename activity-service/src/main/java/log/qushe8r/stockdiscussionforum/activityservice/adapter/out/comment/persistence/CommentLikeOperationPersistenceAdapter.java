package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentLikeOperationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentLikeOperationPersistenceAdapter implements CommentLikeOperationPersistencePort {

    private final CommentLikeJpaRepository repository;

    @Override
    public void likePostByRequestingUser(Long requestingUserId, Long commentId) {
        CommentLikeJpaEntity commentLikeJpaEntity = new CommentLikeJpaEntity(requestingUserId, new CommentJpaEntity(commentId));
        repository.save(commentLikeJpaEntity);
    }

    @Override
    public void unLikePostByRequestingUser(Long requestingUserId, Long commentId) {
        repository.deleteByUserIdAndCommentJpaEntityId(requestingUserId, commentId);
    }

}
