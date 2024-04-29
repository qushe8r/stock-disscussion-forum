package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserDeletionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserDeletionController {

    private final UserDeletionUseCase useCase;

    @DeleteMapping(path = "/{requestedUserId}")
    public ResponseEntity<Void> deleteById(@RequestHeader(name = "userId") Long requestingUserId,
                                           @PathVariable Long requestedUserId) {
        useCase.deleteUser(requestingUserId, requestedUserId);
        return ResponseEntity.ok().build();
    }

}
