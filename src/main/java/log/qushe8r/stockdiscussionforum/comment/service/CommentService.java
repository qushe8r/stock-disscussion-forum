package log.qushe8r.stockdiscussionforum.comment.service;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentCreateRequest;
import log.qushe8r.stockdiscussionforum.comment.dto.CommentModifyRequest;
import log.qushe8r.stockdiscussionforum.comment.entity.Comment;
import log.qushe8r.stockdiscussionforum.comment.exception.CommentException;
import log.qushe8r.stockdiscussionforum.comment.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.comment.mapper.CommentMapper;
import log.qushe8r.stockdiscussionforum.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(CommentCreateRequest request) {
        Comment comment = commentMapper.toEntity(request);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    @Transactional
    public void modifyComment(Long commentId, CommentModifyRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(CommentExceptionCode.COMMENT_NOT_FOUND));
        comment.modify(request.content());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
