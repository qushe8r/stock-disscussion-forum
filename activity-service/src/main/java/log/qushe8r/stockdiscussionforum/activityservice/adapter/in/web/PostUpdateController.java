package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostUpdateUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class PostUpdateController {

    private final PostUpdateUseCase useCase;

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                           @PathVariable Long postId,
                                           @RequestBody PostUpdateCommand command) {
        Long userId = authenticatedUser.getUserId();

        useCase.updatePost(userId, postId, command);
        return ResponseEntity.ok().build();
    }

}
