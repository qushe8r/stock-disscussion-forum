package log.qushe8r.stockdiscussionforum.user.mapper;

import log.qushe8r.stockdiscussionforum.user.dto.UserCreateRequest;
import log.qushe8r.stockdiscussionforum.user.dto.UserDetailsResponse;
import log.qushe8r.stockdiscussionforum.user.dto.UserResponse;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserCreateRequest request, String encodedPassword) {
        return new User(request.username(), request.nickname(), encodedPassword, request.bio());
    }

    public UserDetailsResponse toDetailsResponse(User user) {
        return new UserDetailsResponse(user.getUsername(), user.getNickname(), user.getBio());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getUsername(), user.getNickname(), user.getBio());
    }
}
