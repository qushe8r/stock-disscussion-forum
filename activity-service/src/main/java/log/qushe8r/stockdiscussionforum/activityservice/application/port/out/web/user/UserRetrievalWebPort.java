package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.web.user;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.user.web.UserResponse;

import java.util.List;

public interface UserRetrievalWebPort {

    UserResponse retrieveUserByUserId(Long userId);

    List<UserResponse> retrieveUserByUserIds(String userIds);

}
