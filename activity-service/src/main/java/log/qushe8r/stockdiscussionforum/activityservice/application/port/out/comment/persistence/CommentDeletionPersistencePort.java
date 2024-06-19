package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence;

public interface CommentDeletionPersistencePort {

    void deleteByCommentId(Long commentId);

}
