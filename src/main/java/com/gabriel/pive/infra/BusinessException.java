package com.gabriel.pive.infra;

import org.springframework.http.HttpStatus;

/**
 * Base class for domain exceptions whose HTTP status is intrinsic to the
 * error condition — e.g. "not found" → 404, "already exists" → 409,
 * "invalid input" → 400.
 *
 * <p>Subclasses pass their status and message to this constructor; the
 * global exception handler reads {@link #getStatus()} to choose the
 * correct HTTP response status, so individual handler methods do not
 * need to hard-code it.
 *
 * <p>This class is introduced in Phase 2 of the BovInA refactor as
 * infrastructure for the global error model. Existing custom exceptions
 * (e.g. {@code BullNotFoundException}) continue to extend
 * {@code RuntimeException} for now and are handled by their dedicated
 * per-class methods in {@code GlobalExceptionHandler}; migration of
 * those classes to extend {@code BusinessException} is scheduled for
 * Phase 4.
 */
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status;

    protected BusinessException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
