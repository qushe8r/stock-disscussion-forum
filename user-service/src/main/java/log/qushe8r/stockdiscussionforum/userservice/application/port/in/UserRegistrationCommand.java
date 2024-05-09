package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import log.qushe8r.stockdiscussionforum.common.SelfValidating;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserRole;
import log.qushe8r.stockdiscussionforum.userservice.domain.enumeration.UserStatus;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserRegistrationCommand extends SelfValidating<UserRegistrationCommand> {
    @NotNull
    @Email
    String username;
    @NotNull
    String nickname;
    @NotNull
    String password;
    @NotNull
    String bio;
    String profileImageUrl;
    UserStatus userStatus;
    UserRole userRole;

    public UserRegistrationCommand(String username, String nickname, String password, String bio, String profileImageUrl, UserStatus userStatus, UserRole userRole) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl == null ? "defaultImageUrl" : profileImageUrl;
        this.userStatus = userStatus == null ? UserStatus.INACTIVE : userStatus;
        this.userRole = userRole == null ? UserRole.ROLE_INACTIVE : userRole;
        this.validateSelf();
    }

}
