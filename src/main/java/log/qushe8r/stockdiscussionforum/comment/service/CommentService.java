package log.qushe8r.stockdiscussionforum.comment.service;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentCreateRequest;
import log.qushe8r.stockdiscussionforum.comment.dto.CommentModifyRequest;
import log.qushe8r.stockdiscussionforum.comment.entity.Comment;
import log.qushe8r.stockdiscussionforum.comment.entity.CommentLike;
import log.qushe8r.stockdiscussionforum.comment.exception.CommentException;
import log.qushe8r.stockdiscussionforum.comment.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.comment.mapper.CommentMapper;
import log.qushe8r.stockdiscussionforum.comment.repository.CommentLikeRepository;
import log.qushe8r.stockdiscussionforum.comment.repository.CommentRepository;
import log.qushe8r.stockdiscussionforum.timeline.entity.ActivityType;
import log.qushe8r.stockdiscussionforum.timeline.event.DeliveryFolloweeActivityEvent;
import log.qushe8r.stockdiscussionforum.timeline.event.RemoveFolloweeActivityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Long createComment(Long userId, Long postId, CommentCreateRequest request) {
        Comment comment = commentMapper.toEntity(userId, postId, request);
        Comment savedComment = commentRepository.save(comment);
        Long savedCommentId = savedComment.getId();
        applicationEventPublisher
                .publishEvent(new DeliveryFolloweeActivityEvent(userId, ActivityType.COMMENT, savedCommentId));
        return savedCommentId;
    }

    @Transactional
    public void modifyComment(Long commentId, CommentModifyRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentExceptionCode.COMMENT_NOT_FOUND));
        comment.modify(request.content());
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        commentRepository.deleteByWriterIdAndId(userId, commentId);
        applicationEventPublisher
                .publishEvent(new RemoveFolloweeActivityEvent(userId, ActivityType.COMMENT, commentId));
    }

    @Transactional
    public boolean operateCommentLike(Long userId, Long commentId) {
        Optional<CommentLike> optionalCommentLike =
                commentLikeRepository.findByUserIdAndCommentId(userId, commentId);

        optionalCommentLike.ifPresentOrElse(
                commentLike -> removeCommentLike(commentLike, userId, commentId),
                () -> createCommentLike(userId, commentId));

        return optionalCommentLike.isEmpty();
    }

    private void createCommentLike(Long userId, Long commentId) {
        commentLikeRepository.save(new CommentLike(userId, commentId));
        applicationEventPublisher
                .publishEvent(new DeliveryFolloweeActivityEvent(userId, ActivityType.COMMENT_LIKE, commentId));
    }

    private void removeCommentLike(CommentLike commentLike, Long userId, Long commentId) {
        commentLikeRepository.delete(commentLike);
        applicationEventPublisher
                .publishEvent(new DeliveryFolloweeActivityEvent(userId, ActivityType.COMMENT_LIKE, commentId));
    }

}
