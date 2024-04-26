package log.qushe8r.stockdiscussionforum.activityservice.application.port.in;

import jakarta.validation.constraints.NotNull;
import log.qushe8r.stockdiscussionforum.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class PostRegistrationCommand extends SelfValidating<PostRegistrationCommand> {

    @NotNull
    String title;

    @NotNull
    String content;

    public PostRegistrationCommand(String title, String content) {
        this.title = title;
        this.content = content;
        validateSelf();
    }

}
