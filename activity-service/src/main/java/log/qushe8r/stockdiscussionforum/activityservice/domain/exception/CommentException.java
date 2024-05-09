package log.qushe8r.stockdiscussionforum.activityservice.domain.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;

public class CommentException extends ApplicationException {

    public CommentException(CommentExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
