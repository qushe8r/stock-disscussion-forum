package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web;


import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostDeletionUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostDeletionWebAdapter {

    private final PostDeletionUseCase useCase;

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                           @PathVariable Long postId) {
        Long userId = authenticatedUser.getUserId();

        useCase.deletePost(userId, postId);
        return ResponseEntity.ok().build();
    }

}
