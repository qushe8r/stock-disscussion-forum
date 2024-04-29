package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post;

import log.qushe8r.stockdiscussionforum.common.SelfValidating;
import log.qushe8r.stockdiscussionforum.common.validator.NotSpace;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class PostUpdateCommand extends SelfValidating<PostUpdateCommand> {

    @NotSpace
    String title;

    @NotSpace
    String content;

    public PostUpdateCommand(String title, String content) {
        this.title = title;
        this.content = content;
        validateSelf();
    }

}
