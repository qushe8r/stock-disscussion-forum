package log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment;

import jakarta.validation.constraints.NotNull;
import log.qushe8r.stockdiscussionforum.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CommentUpdateCommand extends SelfValidating<CommentUpdateCommand> {

    @NotNull
    String content;

    String temp;

    public CommentUpdateCommand(String content, String temp) {
        this.content = content;
        this.temp = temp;
        validateSelf();
    }

}
