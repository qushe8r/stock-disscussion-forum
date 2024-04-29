package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.web.UserResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRetrievalUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.web.UserRetrievalWebPort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.common.parser.IdConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostRetrievalService implements PostRetrievalUseCase {

    private final PostMapper mapper;
    private final PostQueryPersistencePort persistencePort;
    private final UserRetrievalWebPort userWebPort;

    @Override
    public PostDetailsResponse findById(Long postId) {
        PostJpaEntity postJpaEntity = persistencePort.findById(postId)
                .orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));

        Long writerId = postJpaEntity.getWriterId();
        UserResponse userDetailsResponse = userWebPort.retrieveUserByUserId(writerId);
        String writerName = userDetailsResponse.nickname();

        Post post = mapper.toDomainEntity(postJpaEntity, writerName);

        return mapper.toResponseDetails(post);
    }

    @Override
    public List<PostResponse> retrievePostsByWriterId(Long writerId) {
        List<PostJpaEntity> postJpaEntities = persistencePort.findByWriterId(writerId);
        List<Long> postIdList = postJpaEntities.stream()
                .map(PostJpaEntity::getWriterId)
                .toList();
        String postIds =  IdConverter.parser(postIdList);
        List<UserResponse> userResponses = userWebPort.retrieveUserByUserIds(postIds);
        HashMap<Long, String> writerIdWriterNickname = new HashMap<>();
        for (UserResponse userResponse : userResponses) {
            writerIdWriterNickname.put(userResponse.userId(), userResponse.nickname());
        }
        return mapper.toResponses(postJpaEntities, writerIdWriterNickname);
    }
}
