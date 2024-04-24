package log.qushe8r.stockdiscussionforum.security.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;

public class JWTException extends ApplicationException {
    public JWTException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
