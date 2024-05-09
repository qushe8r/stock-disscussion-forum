package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.user.web;


import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.web.user.UserRetrievalWebPort;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@WebAdapter
@RequiredArgsConstructor
public class UserRetrievalWebAdapter implements UserRetrievalWebPort {

    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final UserServiceClient userServiceClient;

    @Override
    public UserResponse retrieveUserByUserId(Long userId) {
        return circuitBreaker(() -> userServiceClient.retrieveUser(userId),
                throwable -> new UserResponse(userId, "", ""));
    }

    @Override
    public List<UserResponse> retrieveUserByUserIds(String userIds) {
        return circuitBreaker(() -> userServiceClient.retrieveUsers(userIds),
                throwable -> new ArrayList<>());
    }

    private <T> T circuitBreaker(Supplier<T> supplier, Function<Throwable, T> function) {
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("user-service");
        return circuitbreaker.run(supplier, function);
    }

}
