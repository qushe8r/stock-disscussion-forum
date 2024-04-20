package log.qushe8r.stockdiscussionforum.comment.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;

public class CommentException extends ApplicationException {
    public CommentException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
