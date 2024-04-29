package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRetrievalUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRetrievalWebAdapter {

    private final PostRetrievalUseCase useCase;

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailsResponse> retrievePostDetails(@PathVariable Long postId) {
        PostDetailsResponse response = useCase.findById(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<PostResponse>> retrievePostResponseByUser(@RequestHeader Long userId) {
        List<PostResponse> responses = useCase.retrievePostsByWriterId(userId);
        return ResponseEntity.ok(responses);
    }

//    @GetMapping("/")

}
