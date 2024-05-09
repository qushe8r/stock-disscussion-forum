package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserPasswordUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserUpdateController {

    private final UserUpdateUseCase useCase;

    @PatchMapping("/me")
    public ResponseEntity<Void> userUpdateInformation(@RequestHeader Long userId,
                                                      @RequestBody UserUpdateCommand command) {
        useCase.updateUserInformation(userId, command);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/password")
    public ResponseEntity<Void> userUpdatePassword(@RequestHeader Long userId,
                                                   @RequestBody UserPasswordUpdateCommand command) {
        useCase.updateUserPassword(userId, command);
        return ResponseEntity.ok().build();
    }

}
