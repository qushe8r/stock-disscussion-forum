package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostUpdateUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostUpdateController {

    private final PostUpdateUseCase useCase;

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@RequestHeader Long userId,
                                           @PathVariable Long postId,
                                           @RequestBody PostUpdateCommand command) {
        useCase.updatePost(userId, postId, command);
        return ResponseEntity.ok().build();
    }

}
