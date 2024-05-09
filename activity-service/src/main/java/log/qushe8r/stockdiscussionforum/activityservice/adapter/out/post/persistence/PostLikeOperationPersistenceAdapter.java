package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostLikeOperationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostLikeOperationPersistenceAdapter implements PostLikeOperationPersistencePort {

    private final PostLikeJpaRepository postLikeJpaRepository;

    @Override
    public void likePostByRequestingUser(Long requestingUserId, Long postId) {
        PostLikeJpaEntity postLikeJpaEntity = new PostLikeJpaEntity(requestingUserId, new PostJpaEntity(postId));
        postLikeJpaRepository.save(postLikeJpaEntity);
    }

    @Override
    public void unLikePostByRequestingUser(Long requestingUserId, Long postId) {
        postLikeJpaRepository.deleteByUserIdAndPostJpaEntityId(requestingUserId, postId);
    }

}
