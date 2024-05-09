package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web.post;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostLikeOperationUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostLikeOperationWebAdapter {

    private final PostLikeOperationUseCase useCase;

    @PostMapping(path = "/{postId}/likes")
    public ResponseEntity<Boolean> operatePostLike(@RequestHeader(name = "userId") Long requestingUserId,
                                                   @PathVariable Long postId) {
        boolean response = useCase.operatePostLike(requestingUserId, postId);
        return ResponseEntity.ok(response);
    }

}
