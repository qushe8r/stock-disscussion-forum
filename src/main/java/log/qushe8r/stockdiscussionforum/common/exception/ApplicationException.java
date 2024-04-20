package log.qushe8r.stockdiscussionforum.common.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public ApplicationException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
