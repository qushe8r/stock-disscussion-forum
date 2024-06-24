package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web.comment;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentLikeOperationUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentLikeOperationWebAdapter {

    private final CommentLikeOperationUseCase useCase;

    @PostMapping("/{commentId}/likes")
    public ResponseEntity<Boolean> operateCommentLike(@RequestHeader("userId") Long requestingUserId,
                                                      @PathVariable Long commentId) {
        boolean response = useCase.operateCommentLike(requestingUserId, commentId);
        return ResponseEntity.ok(response);
    }

}
