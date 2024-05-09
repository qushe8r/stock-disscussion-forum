package log.qushe8r.stockdiscussionforum.newfeedservice.domain.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;

public class FollowException extends ApplicationException {

    public FollowException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
