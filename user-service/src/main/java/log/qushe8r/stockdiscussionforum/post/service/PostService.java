package log.qushe8r.stockdiscussionforum.post.service;

import log.qushe8r.stockdiscussionforum.post.dto.PostCreateRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.post.dto.PostModifyRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostResponse;
import log.qushe8r.stockdiscussionforum.post.entity.Post;
import log.qushe8r.stockdiscussionforum.post.entity.PostLike;
import log.qushe8r.stockdiscussionforum.post.exception.PostException;
import log.qushe8r.stockdiscussionforum.post.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.post.mapper.PostMapper;
import log.qushe8r.stockdiscussionforum.post.repository.PostLikeRepository;
import log.qushe8r.stockdiscussionforum.post.repository.PostRepository;
import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;
import log.qushe8r.stockdiscussionforum.timeline.event.DeliveryFolloweeActivityEvent;
import log.qushe8r.stockdiscussionforum.timeline.event.RemoveFolloweeActivityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Long createPost(Long userId, PostCreateRequest request) {
        Post post = postMapper.toEntity(userId, request);
        Post savedPost = postRepository.save(post);
        applicationEventPublisher
                .publishEvent(new DeliveryFolloweeActivityEvent(userId, ActivityType.POST, post.getId()));
        return savedPost.getId();
    }

    @Transactional
    public void modifyPost(Long postId, PostModifyRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));
        post.modify(request.title(), request.content());
    }

    public PostDetailsResponse getPost(Long postId) {
        return postRepository.findById(postId)
                .map(postMapper::toDetailsResponse)
                .orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));
    }

    public List<PostResponse> getPosts() {
        return postRepository.findAll()
                .stream().map(postMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deletePost(Long userId, Long postId) {
        postRepository.deleteByWriterIdAndId(userId, postId);
    }

    @Transactional
    public boolean operatePostLike(Long userId, Long postId) {
        Optional<PostLike> optionalPostLike = postLikeRepository.findByUserIdAndPostId(userId, postId);

        optionalPostLike.ifPresentOrElse(
                postLike -> removePostLikeProcess(postLike, userId, postId),
                () -> createPostLikeProcess(userId, postId));

        return optionalPostLike.isEmpty();
    }

    private void removePostLikeProcess(PostLike postLike, Long userId, Long postId) {
        postLikeRepository.delete(postLike);
        applicationEventPublisher
                .publishEvent(new RemoveFolloweeActivityEvent(userId, ActivityType.POST_LIKE, postId));
    }

    private void createPostLikeProcess(Long userId, Long postId) {
        PostLike postLike = new PostLike(userId, postId);
        postLikeRepository.save(postLike);
        applicationEventPublisher
                .publishEvent(new DeliveryFolloweeActivityEvent(userId, ActivityType.POST_LIKE, postId));
    }

}
