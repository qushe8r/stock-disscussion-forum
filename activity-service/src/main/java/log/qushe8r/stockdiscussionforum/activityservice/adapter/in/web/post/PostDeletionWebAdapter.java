package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web.post;


import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostDeletionUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostDeletionWebAdapter {

    private final PostDeletionUseCase useCase;

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@RequestHeader Long userId,
                                           @PathVariable Long postId) {
        useCase.deletePost(userId, postId);
        return ResponseEntity.ok().build();
    }

}
