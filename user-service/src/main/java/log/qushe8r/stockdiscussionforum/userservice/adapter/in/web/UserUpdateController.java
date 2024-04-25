package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserPasswordUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserUpdateController {

    private final UserUpdateUseCase useCase;

    @PatchMapping("/me")
    public ResponseEntity<Void> userUpdateInformation(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                      @RequestBody UserUpdateCommand command) {
        Long userId = authenticatedUser.getUserId();

        useCase.updateUserInformation(userId, command);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/password")
    public ResponseEntity<Void> userUpdatePassword(@AuthenticationPrincipal AuthenticatedUser authenticatedUser,
                                                   @RequestBody UserPasswordUpdateCommand command) {
        Long userId = authenticatedUser.getUserId();

        useCase.updateUserPassword(userId, command);
        return ResponseEntity.ok().build();
    }

}
