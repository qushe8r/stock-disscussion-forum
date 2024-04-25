package log.qushe8r.stockdiscussionforum.common.dto;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public record FieldErrorResponse(String field, String rejectedValue, String reason) {
    public static List<FieldErrorResponse> of(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldErrorResponse::toFieldError)
                .toList();
    }

    private static FieldErrorResponse toFieldError(FieldError error) {
        Object rejectedValue = error.getRejectedValue();
        return new FieldErrorResponse(
                error.getField(),
                rejectedValue == null ? "" : rejectedValue.toString(),
                error.getDefaultMessage());
    }
}
