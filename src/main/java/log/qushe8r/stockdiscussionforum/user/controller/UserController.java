package log.qushe8r.stockdiscussionforum.user.controller;

import log.qushe8r.stockdiscussionforum.user.dto.UserCreateRequest;
import log.qushe8r.stockdiscussionforum.user.dto.UserDetailsResponse;
import log.qushe8r.stockdiscussionforum.user.dto.UserModifyRequest;
import log.qushe8r.stockdiscussionforum.user.dto.UserResponse;
import log.qushe8r.stockdiscussionforum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) {
        userService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sign-up/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam Long userId, @RequestParam String code) {
        String response = userService.verifyCode(userId, code);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<Void> modifyUser(@PathVariable long userId, @RequestBody UserModifyRequest request) {
        userService.modify(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> responses = userService.getAll();
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDetailsResponse> getUser(@PathVariable long userId) {
        UserDetailsResponse response = userService.get(userId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
