package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

public record UserDetailsResponse(Long userId, String username, String nickname, String bio, String profileImageUrl) {
}
