package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web.comment;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentRegistrationWebAdapter {

    private final CommentRegistrationUseCase useCase;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Void> registerComment(@RequestHeader Long userId,
                                                @PathVariable Long postId,
                                                @RequestBody CommentRegistrationCommand command) {
        useCase.registerComment(userId, postId, command);
        return ResponseEntity.ok().build();
    }

}
