package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.user.web.UserResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentLikeOperationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentLikeOperationPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.web.user.UserRetrievalWebPort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class CommentLikeOperationService implements CommentLikeOperationUseCase {

    private final CommentMapper mapper;
    private final CommentQueryPersistencePort queryPort;
    private final CommentLikeOperationPersistencePort persistencePort;
    private final UserRetrievalWebPort userWebPort;

    @Transactional
    @Override
    public boolean operateCommentLike(Long requestingUserId, Long commentId) {
        CommentJpaEntity commentJpaEntity = queryPort.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentExceptionCode.COMMENT_NOT_FOUND));

        Long writerId = commentJpaEntity.getWriterId();
        Map<Long, String> writerIdNickname = retrieveWriterNickname(writerId);
        Comment comment = mapper.toDomainEntityPostWithIdOnly(commentJpaEntity, writerIdNickname);

        return comment.togglePostLikeForRequestingUser(
                requestingUserId,
                persistencePort::likePostByRequestingUser,
                persistencePort::unLikePostByRequestingUser);
    }

    private Map<Long, String> retrieveWriterNickname(Long writerId) {
        UserResponse userResponse = userWebPort.retrieveUserByUserId(writerId);
        return Map.of(writerId, userResponse.nickname());
    }

}
