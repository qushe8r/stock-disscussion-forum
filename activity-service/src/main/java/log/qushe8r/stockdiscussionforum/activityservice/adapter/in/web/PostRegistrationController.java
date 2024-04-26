package log.qushe8r.stockdiscussionforum.activityservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostRegistrationController {

    private final PostRegistrationUseCase useCase;

    @PostMapping
    public ResponseEntity<Void> d(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                  @RequestBody PostRegistrationCommand command) {
        Long userId = authenticatedUser.getUserId();

        useCase.registerPost(userId, command);
        return ResponseEntity.ok().build();
    }

}
