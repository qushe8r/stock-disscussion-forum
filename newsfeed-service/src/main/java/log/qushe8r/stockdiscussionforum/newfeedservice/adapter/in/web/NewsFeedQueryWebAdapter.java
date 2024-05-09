package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedQueryUseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/newsfeeds")
public class NewsFeedQueryWebAdapter {

    private final NewsFeedQueryUseCase useCase;

    @GetMapping
    public ResponseEntity<List<NewsFeedResponse>> queryNewsFeeds(@RequestHeader(name = "userId") Long requestingUserId) {
        List<NewsFeedResponse> responses = useCase.queryNewsFeeds(requestingUserId);
        return ResponseEntity.ok(responses);
    }

}
