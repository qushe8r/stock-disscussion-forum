package log.qushe8r.stockdiscussionforum.newfeedservice.domain.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FollowExceptionCode implements ExceptionCode {
    CANNOT_FOLLOW_SELF("FX01", 404, "스스로를 팔로우 할 수 없습니다.");

    private final String errorCode;

    private final Integer status;

    private final String message;

}
