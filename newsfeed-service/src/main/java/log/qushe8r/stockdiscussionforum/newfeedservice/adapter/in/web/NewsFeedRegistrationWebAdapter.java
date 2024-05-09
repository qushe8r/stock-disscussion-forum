package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.in.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationCommand;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/newsfeeds")
public class NewsFeedRegistrationWebAdapter {

    private final NewsFeedRegistrationUseCase useCase;

    @PostMapping
    public ResponseEntity<Void> registerNewsFeed(NewsFeedRegistrationCommand command) {
        useCase.registerNewsFeed(command);
        return ResponseEntity.ok().build();
    }

}
