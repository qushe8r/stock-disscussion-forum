package log.qushe8r.stockdiscussionforum.activityservice.domain.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentExceptionCode implements ExceptionCode {

    COMMENT_NOT_FOUND("CX01", 404, "댓글을 찾을 수 없습니다."),
    CANNOT_CHANGE_INFORMATION("CX03", 400, "정보 변경을 할 수 없습니다.");

    private final String errorCode;

    private final Integer status;

    private final String message;

}
