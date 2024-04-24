package log.qushe8r.stockdiscussionforum.user.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserExceptionCode implements ExceptionCode {
    USER_NOT_FOUND("UX01", 404, "유저를 찾을 수 없습니다."),
    USER_ALREADY_EXIST("UX02", 400, "이미 존재하는 이메일입니다."),
    CANNOT_CHANGE_INFORMATION("UX03", 400, "정보 변경을 할 수 없습니다.");

    private final String errorCode;

    private final Integer status;

    private final String message;
}
