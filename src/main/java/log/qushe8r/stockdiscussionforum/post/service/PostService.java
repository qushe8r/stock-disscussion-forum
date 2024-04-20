package log.qushe8r.stockdiscussionforum.post.service;

import log.qushe8r.stockdiscussionforum.post.dto.PostCreateRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.post.dto.PostModifyRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostResponse;
import log.qushe8r.stockdiscussionforum.post.entity.Post;
import log.qushe8r.stockdiscussionforum.post.exception.PostException;
import log.qushe8r.stockdiscussionforum.post.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.post.mapper.PostMapper;
import log.qushe8r.stockdiscussionforum.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostCreateRequest request) {
        Post post = postMapper.toEntity(request);
        Post savedPost = postRepository.save(post);
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
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

}
