package log.qushe8r.stockdiscussionforum.activityservice.domain.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostExceptionCode implements ExceptionCode {

    POST_NOT_FOUND("PX01", 404, "게시글을 찾을 수 없습니다."),
    CANNOT_CHANGE_INFORMATION("PX03", 400, "정보 변경을 할 수 없습니다."),
    CANNOT_POST_LIKE_SELF("PX04", 400, "자신의 글을 좋아요 표시할 수 없습니다.");

    private final String errorCode;

    private final Integer status;

    private final String message;

}
