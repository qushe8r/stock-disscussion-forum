package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web.comment;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentUpdateUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentUpdateWebAdapter {

    public final CommentUpdateUseCase useCase;

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@RequestHeader Long userId,
                                              @PathVariable Long commentId,
                                              @RequestBody CommentUpdateCommand command) {
        useCase.updateComment(userId, commentId, command);
        return ResponseEntity.ok().build();
    }

}
