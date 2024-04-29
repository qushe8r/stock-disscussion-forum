package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.user.web;


import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.web.user.UserRetrievalWebPort;
import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
public class UserRetrievalWebAdapter implements UserRetrievalWebPort {

    private final UserServiceClient userServiceClient;

    @Override
    public UserResponse retrieveUserByUserId(Long userId) {
        return userServiceClient.retrieveUser(userId);
    }

    @Override
    public List<UserResponse> retrieveUserByUserIds(String userIds) {
        return userServiceClient.retrieveUsers(userIds);
    }

}
