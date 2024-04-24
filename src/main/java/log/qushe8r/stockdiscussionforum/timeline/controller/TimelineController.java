package log.qushe8r.stockdiscussionforum.timeline.controller;

import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.timeline.dto.TimelineResponse;
import log.qushe8r.stockdiscussionforum.timeline.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/timelines")
public class TimelineController {
    private final TimelineService timelineService;

    @GetMapping("/me")
    public ResponseEntity<List<TimelineResponse>> find(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getUserId();

        List<TimelineResponse> responses = timelineService.findByFollowerId(userId);
        return ResponseEntity.ok(responses);
    }

}
