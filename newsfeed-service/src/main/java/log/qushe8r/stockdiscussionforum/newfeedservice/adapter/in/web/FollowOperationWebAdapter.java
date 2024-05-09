package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.FollowOperationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowOperationWebAdapter {

    private final FollowOperationUseCase useCase;

    @PostMapping("/{followeeId}")
    public ResponseEntity<Boolean> operateFollow(@RequestHeader(name = "userId") Long requestingUserId,
                                                 @PathVariable(name = "followeeId") Long followeeId) {
        boolean response = useCase.operateFollow(requestingUserId, followeeId);
        return ResponseEntity.ok(response);
    }

}
