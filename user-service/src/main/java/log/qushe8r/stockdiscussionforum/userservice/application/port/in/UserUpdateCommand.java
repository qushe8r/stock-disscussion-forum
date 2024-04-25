package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

import log.qushe8r.stockdiscussionforum.common.SelfValidating;
import log.qushe8r.stockdiscussionforum.common.validator.NotSpace;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserUpdateCommand extends SelfValidating<UserUpdateCommand> {

    @NotSpace
    String nickname;

    @NotSpace
    String bio;

    @NotSpace
    String profileImageUrl;

    public UserUpdateCommand(String nickname, String bio, String profileImageUrl) {
        this.nickname = nickname;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        validateSelf();
    }

}
