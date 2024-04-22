package log.qushe8r.stockdiscussionforum.security.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ApplicationException;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;

public class JwtException extends ApplicationException {
    public JwtException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
