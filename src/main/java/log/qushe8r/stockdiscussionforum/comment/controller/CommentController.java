package log.qushe8r.stockdiscussionforum.comment.controller;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentModifyRequest;
import log.qushe8r.stockdiscussionforum.comment.dto.CommentResponse;
import log.qushe8r.stockdiscussionforum.comment.service.CommentService;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponse> modifyComment(@PathVariable Long commentId,
                                                         @RequestBody CommentModifyRequest request) {
        commentService.modifyComment(commentId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                              @PathVariable Long commentId) {
        Long userId = authenticatedUser.getUserId();

        commentService.deleteComment(userId, commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{commentId}/likes")
    public ResponseEntity<Boolean> operateCommentLike(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                      @PathVariable Long commentId) {
        Long userId = authenticatedUser.getUserId();

        boolean response = commentService.operateCommentLike(userId, commentId);
        return ResponseEntity.ok().body(response);
    }

}
