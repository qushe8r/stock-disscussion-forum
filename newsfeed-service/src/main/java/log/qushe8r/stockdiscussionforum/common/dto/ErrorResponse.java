package log.qushe8r.stockdiscussionforum.common.dto;

import jakarta.validation.ConstraintViolation;
import log.qushe8r.stockdiscussionforum.common.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;

public record ErrorResponse(String errorCode,
                            Integer status,
                            String message,
                            List<FieldErrorResponse> fieldErrorResponses,
                            List<ConstraintViolationResponse> violationErrors) {

    private ErrorResponse(String errorCode, int status, String message) {
        this(errorCode, status, message, null, null);
    }

    private ErrorResponse(List<FieldErrorResponse> fieldErrorResponses, List<ConstraintViolationResponse> violationErrors) {
        this(null, null, null, fieldErrorResponses, violationErrors);
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldErrorResponse.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationResponse.of(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(
                exceptionCode.getErrorCode(), exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(null, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(null, httpStatus.value(), message);
    }
}
