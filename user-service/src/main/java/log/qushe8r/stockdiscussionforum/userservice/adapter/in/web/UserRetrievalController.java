package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.common.parser.IdConverter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserResponse;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRetrievalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRetrievalController {

    private final UserRetrievalUseCase useCase;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long userId) {
        UserResponse userResponse = useCase.retrieveUserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<UserResponse> findByUsername(@RequestParam("username") String username) {
        UserResponse userResponse = useCase.retrieveUserByUsername(username);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/find-by-ids")
    public ResponseEntity<List<UserResponse>> findByIds(@RequestParam("userIds") String userIds) {
        List<Long> userIdsList = IdConverter.parser(userIds);
        List<UserResponse> responses = useCase.retrieveUsersByIds(userIdsList);
        return ResponseEntity.ok(responses);
    }

}
