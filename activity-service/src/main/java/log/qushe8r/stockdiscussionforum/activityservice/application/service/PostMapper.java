package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostJpaEntity toJpaEntity(Long writerId, PostRegistrationCommand postRegistrationCommand) {
        String title = postRegistrationCommand.getTitle();
        String content = postRegistrationCommand.getContent();

        return new PostJpaEntity(title, content, writerId);
    }
}
