package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

import log.qushe8r.stockdiscussionforum.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Objects;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserPasswordUpdateCommand extends SelfValidating<UserPasswordUpdateCommand> {

    String oldPassword;

    String newPassword;

    public UserPasswordUpdateCommand(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        if (Objects.equals(oldPassword, newPassword)) {
            throw new IllegalArgumentException("Old and new passwords do not match");
        }
        validateSelf();
    }
}
