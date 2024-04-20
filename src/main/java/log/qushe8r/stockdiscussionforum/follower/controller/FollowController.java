package log.qushe8r.stockdiscussionforum.follower.controller;

import log.qushe8r.stockdiscussionforum.follower.dto.FollowOperationRequest;
import log.qushe8r.stockdiscussionforum.follower.service.FollowService;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Boolean> operateFollow(@RequestBody FollowOperationRequest request,
                                                 @AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        boolean response = followService.operateFollow(request, authenticatedUser);
        return ResponseEntity.ok().body(response);
    }

}
