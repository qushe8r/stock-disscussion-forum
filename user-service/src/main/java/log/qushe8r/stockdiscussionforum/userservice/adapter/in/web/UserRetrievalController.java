package log.qushe8r.stockdiscussionforum.userservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.common.parser.IdConverter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserDetailsResponse;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserResponse;
import log.qushe8r.stockdiscussionforum.userservice.application.port.in.UserRetrievalUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserRetrievalController {

    private final UserRetrievalUseCase useCase;

    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> retrieveSelfDetails(
            @RequestHeader(name = "userId") String requestHeaderUserId
    ) {
        Long userId = Long.parseLong(requestHeaderUserId);
        UserDetailsResponse userResponse = useCase.retrieveSelfDetails(userId);
        return ResponseEntity.ok(userResponse);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> retrieveUserById(@PathVariable Long userId) {
        UserResponse userResponse = useCase.retrieveUserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<UserResponse> retrieveUserByUsername(
            @RequestParam(name = "email", required = false) String email
    ) {
        UserResponse userResponse = useCase.retrieveUserByUsername(email);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/find-by-ids")
    public ResponseEntity<List<UserResponse>> retrieveUsersByIds(@RequestParam("userIds") String userIds) {
        log.info("userIds = {}", userIds);
        List<Long> userIdsList = IdConverter.parser(userIds);
        List<UserResponse> responses = useCase.retrieveUsersByIds(userIdsList);
        return ResponseEntity.ok(responses);
    }

}
