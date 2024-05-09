package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedDeletionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/newsfeeds")
public class NewsFeedDeletionWebAdapter {

    private final NewsFeedDeletionUseCase useCase;

    @DeleteMapping("/{followeeId}/{activityId}")
    public ResponseEntity<Void> deleteNewsFeed(@PathVariable Long followeeId, @PathVariable Long activityId) {
        useCase.deleteNewsFeeds(followeeId, activityId);
        return ResponseEntity.ok().build();
    }

}
