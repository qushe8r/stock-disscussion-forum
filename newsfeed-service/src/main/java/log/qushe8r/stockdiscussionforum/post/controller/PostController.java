package log.qushe8r.stockdiscussionforum.post.controller;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentCreateRequest;
import log.qushe8r.stockdiscussionforum.comment.service.CommentService;
import log.qushe8r.stockdiscussionforum.post.dto.PostCreateRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.post.dto.PostModifyRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostResponse;
import log.qushe8r.stockdiscussionforum.post.service.PostService;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createPost(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                           @RequestBody PostCreateRequest request) {
        Long userId = authenticatedUser.getUserId();

        Long response = postService.createPost(userId, request);
        URI location = UriComponentsBuilder.fromUriString("/posts/{postId}")
                .path(String.valueOf(response))
                .build()
                .toUri();
        return ResponseEntity.ok().location(location).build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> modifyPost(@PathVariable Long postId, @RequestBody PostModifyRequest request) {
        postService.modifyPost(postId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailsResponse> getPost(@PathVariable Long postId) {
        PostDetailsResponse response = postService.getPost(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getPosts() {
        List<PostResponse> responses = postService.getPosts();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                           @PathVariable Long postId) {
        Long userId = authenticatedUser.getUserId();

        postService.deletePost(userId, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Void> createComment(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                              @PathVariable Long postId,
                                              @RequestBody CommentCreateRequest request) {
        Long writerId = authenticatedUser.getUserId();

        Long commentId = commentService.createComment(writerId, postId, request);
        URI location = UriComponentsBuilder.fromUriString("/api/comments/{commentId}")
                .buildAndExpand(commentId)
                .toUri();
        return ResponseEntity.ok().location(location).build();
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Boolean> operatePostLike(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                   @PathVariable Long postId) {
        Long userId = authenticatedUser.getUserId();

        boolean response = postService.operatePostLike(userId, postId);
        return ResponseEntity.ok(response);
    }

}
