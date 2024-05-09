package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationCommand;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRegistrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRegistrationController {

    private final UserRegistrationUseCase useCase;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationCommand command) {
        useCase.registerUser(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sign-up/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam Long userId, @RequestParam String code) {
        String response = useCase.verifyCode(userId, code);
        return ResponseEntity.ok(response);
    }

}
