package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserUpdateController {

    private final UserUpdateUseCase useCase;

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> userUpdateInformation(@PathVariable Long userId,
                                                      @RequestBody UserUpdateCommand command) {
        useCase.userUpdateInformation(userId, command);
        return ResponseEntity.ok().build();
    }

}
