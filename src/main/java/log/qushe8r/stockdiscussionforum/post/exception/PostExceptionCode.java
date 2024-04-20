package log.qushe8r.stockdiscussionforum.post.exception;

import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostExceptionCode implements ExceptionCode {
    POST_NOT_FOUND("PX01", 404, "게시글을 찾을 수 없습니다.");

    private final String errorCode;

    private final Integer status;

    private final String message;
}
