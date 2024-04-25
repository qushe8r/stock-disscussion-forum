package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserDeletionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserDeletionController {

    private final UserDeletionUseCase useCase;

    @DeleteMapping(path = "/{requestedUserId}")
    public ResponseEntity<Void> deleteById(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                           @PathVariable Long requestedUserId) {
        Long requestingUserId = authenticatedUser.getUserId();

        useCase.deleteUser(requestingUserId, requestedUserId);
        return ResponseEntity.ok().build();
    }

}
