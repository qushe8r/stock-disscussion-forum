package log.qushe8r.stockdiscussionforum.activityservice.domain.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;

public class PostException extends ApplicationException {

    public PostException(PostExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
