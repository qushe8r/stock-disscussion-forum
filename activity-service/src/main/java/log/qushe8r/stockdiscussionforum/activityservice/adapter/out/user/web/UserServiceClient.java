package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.user.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/{userId}")
    UserResponse retrieveUser(@PathVariable Long userId);

    @GetMapping("/api/users/find-by-ids")
    List<UserResponse> retrieveUsers(@RequestParam String userIds);

}
