package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentDeletionPersistenceAdapter implements CommentDeletionPersistencePort {

    private final CommentJpaRepository repository;

    @Override
    public void deleteByCommentId(Long commentId) {
        repository.deleteById(commentId);
    }

}
