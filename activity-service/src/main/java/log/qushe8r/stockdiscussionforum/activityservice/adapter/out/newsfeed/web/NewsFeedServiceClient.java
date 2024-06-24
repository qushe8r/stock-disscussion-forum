package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "newsfeed-service")
public interface NewsFeedServiceClient {

	@PostMapping("/api/newsfeeds")
	ResponseEntity<Void> registerNewsfeeds(@RequestBody NewsFeedCommand command);

}
