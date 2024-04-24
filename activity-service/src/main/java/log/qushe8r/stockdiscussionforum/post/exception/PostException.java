package log.qushe8r.stockdiscussionforum.post.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;

public class PostException extends ApplicationException {
    public PostException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
