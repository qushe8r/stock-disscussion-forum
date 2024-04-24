package log.qushe8r.stockdiscussionforum.user.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;

public class UserException extends ApplicationException {

    public UserException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
