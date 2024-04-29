package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web.comment;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentDeletionUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentDeletionWebAdapter {

    private final CommentDeletionUseCase useCase;

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@RequestHeader Long userId,
                                              @PathVariable Long commentId) {
        useCase.deleteComment(userId, commentId);
        return ResponseEntity.ok().build();
    }

}
