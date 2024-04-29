package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.user.web.UserResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRetrievalUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.web.user.UserRetrievalWebPort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.common.parser.IdConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        String writerIds = getWriterIds(postJpaEntity);
        Map<Long, String> writerIdNickname = retrieveWriterIdNicknameMapping(writerIds);
        Post post = mapper.toDomainEntity(postJpaEntity, writerIdNickname);

        return mapper.toResponseDetails(post);
    }

    @Override
    public List<PostResponse> retrievePostsByWriterId(Long writerId) {
        List<PostJpaEntity> postJpaEntities = persistencePort.findByWriterId(writerId);
        String writerIds = getWriterIds(postJpaEntities);
        Map<Long, String> writerIdNickname = retrieveWriterIdNicknameMapping(writerIds);

        return mapper.toResponses(postJpaEntities, writerIdNickname);
    }

    private String getWriterIds(List<PostJpaEntity> postJpaEntities) {
        List<Long> postWriterIds = postJpaEntities.stream()
                .map(PostJpaEntity::getWriterId)
                .toList();

        ArrayList<Long> writerIds = postJpaEntities.stream()
                .map(PostJpaEntity::getCommentJpaEntities)
                .flatMap(commentJpaEntities -> commentJpaEntities.stream()
                        .map(CommentJpaEntity::getWriterId))
                .collect(Collectors.toCollection(ArrayList::new));

        writerIds.addAll(postWriterIds);

        return IdConverter.parser(writerIds);
    }

    private String getWriterIds(PostJpaEntity postJpaEntity) {
        Long writerId = postJpaEntity.getWriterId();

        ArrayList<Long> writerIds = postJpaEntity.getCommentJpaEntities().stream()
                .map(CommentJpaEntity::getWriterId)
                .collect(Collectors.toCollection(ArrayList::new));

        writerIds.add(writerId);
        return IdConverter.parser(writerIds);
    }


    private Map<Long, String> retrieveWriterIdNicknameMapping(String writerIds) {
        Map<Long, String> writerIdWriterNicknameMap = new HashMap<>();

        List<UserResponse> userResponses = userWebPort.retrieveUserByUserIds(writerIds);
        for (UserResponse userResponse : userResponses) {
            writerIdWriterNicknameMap.put(userResponse.userId(), userResponse.nickname());
        }

        return writerIdWriterNicknameMap;
    }

}
