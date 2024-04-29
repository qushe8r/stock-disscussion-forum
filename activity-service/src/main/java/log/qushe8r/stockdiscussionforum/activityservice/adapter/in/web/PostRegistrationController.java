package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRegistrationController {

    private final PostRegistrationUseCase useCase;

    @PostMapping
    public ResponseEntity<Void> registerPost(@RequestHeader Long userId,
                                             @RequestBody PostRegistrationCommand command) {
        useCase.registerPost(userId, command);
        return ResponseEntity.ok().build();
    }

}
