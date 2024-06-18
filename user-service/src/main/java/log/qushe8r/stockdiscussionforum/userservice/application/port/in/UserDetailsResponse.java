package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserRole;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserStatus;

public record UserDetailsResponse(
		Long userId,
		String username,
		String nickname,
		String bio,
		String profileImageUrl,
		UserStatus userStatus,
		UserRole role
) {
}
