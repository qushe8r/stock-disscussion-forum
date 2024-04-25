package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

import java.util.List;

public interface UserRetrievalUseCase {

    UserResponse retrieveUserById(Long userId);

    UserResponse retrieveUserByUsername(String username);

    List<UserResponse> retrieveUsersByIds(List<Long> userIds);

}
