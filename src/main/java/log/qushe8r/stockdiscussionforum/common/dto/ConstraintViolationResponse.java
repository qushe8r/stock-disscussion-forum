package log.qushe8r.stockdiscussionforum.common.dto;

import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Set;

public record ConstraintViolationResponse(String propertyPath, String rejectedValue, String reason) {
    public static List<ConstraintViolationResponse> of(
            Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(ConstraintViolationResponse::toResponse)
                .toList();
    }

    private static ConstraintViolationResponse toResponse(ConstraintViolation<?> constraintViolation) {
        return new ConstraintViolationResponse(
                constraintViolation.getPropertyPath().toString(),
                constraintViolation.getInvalidValue().toString(),
                constraintViolation.getMessage());
    }
}
