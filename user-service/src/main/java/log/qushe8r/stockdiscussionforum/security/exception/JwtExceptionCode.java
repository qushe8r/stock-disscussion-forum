package log.qushe8r.stockdiscussionforum.security.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtExceptionCode implements ExceptionCode {
    ACCESS_TOKEN_EXPIRED("JX01", 401, "액세스 토큰이 만료 되었습니다."),
    INVALID_TOKEN("JX02", 401, "유효하지 않은 토큰입니다."),
    TOKEN_NOT_FOUND("JX03", 401, "토큰이 없습니다.");

    private final String errorCode;

    private final Integer status;

    private final String message;
}
