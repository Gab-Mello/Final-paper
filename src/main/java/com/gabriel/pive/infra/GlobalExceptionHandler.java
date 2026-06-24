package com.gabriel.pive.infra;

import com.gabriel.pive.fiv.pregnancy.exceptions.ReceiverCattleDoesNotHaveAnEmbryoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Gate for the error response body shape. When {@code true} (the default),
     * handlers return a raw-string body — the legacy contract that the current
     * frontend expects. When {@code false}, returns an RFC 7807 ProblemDetail
     * JSON body.
     */
    @Value("${bovina.error.legacy-format:true}")
    private boolean legacyFormat;

    /**
     * Single seam that every handler funnels through. When {@link #legacyFormat}
     * is {@code true} (the default), returns the legacy raw-string body that the
     * existing frontend expects. When {@code false}, returns an RFC 7807
     * {@link ProblemDetail} JSON body with {@code type}, {@code title},
     * {@code status}, {@code detail}, {@code instance} (the request path) and a
     * {@code timestamp} extension field.
     */
    private ResponseEntity<Object> buildBody(HttpStatus status, String message, HttpServletRequest request) {
        if (legacyFormat) {
            return ResponseEntity.status(status).body(message);
        }
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, message);
        problem.setTitle(status.getReasonPhrase());
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now().toString());
        return ResponseEntity.status(status).body(problem);
    }

    /**
     * Catch-all for any exception that declares its own HTTP status by
     * extending {@link BusinessException}. The status comes from the exception
     * itself ({@link BusinessException#getStatus()}), so adding a new domain
     * exception requires no edit to this handler.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception, HttpServletRequest request){
        return buildBody(exception.getStatus(), exception.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(error -> error instanceof FieldError fieldError
                        ? fieldError.getField() + ": " + fieldError.getDefaultMessage()
                        : error.getDefaultMessage())
                .collect(java.util.stream.Collectors.joining("; "));
        return buildBody(HttpStatus.BAD_REQUEST, errorMessage, request);
    }

    @ExceptionHandler(ReceiverCattleDoesNotHaveAnEmbryoException.class)
    public ResponseEntity<Object> receiverDoesNotHaveAnEmbryo(ReceiverCattleDoesNotHaveAnEmbryoException exception, HttpServletRequest request){
        return buildBody(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    /**
     * JPA's {@link EntityNotFoundException} is thrown when code reaches into a lazy
     * proxy whose underlying row no longer exists. Map it to 404 so controllers
     * don't need their own try/catch blocks (the {@code ScheduleController}'s
     * existing try/catch can be removed in a later phase).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(EntityNotFoundException exception, HttpServletRequest request){
        return buildBody(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    /**
     * Last-resort catch-all. Any exception that no other handler claimed lands
     * here. We log the full stack at ERROR (server-side observability) and
     * respond with a safe, generic 500 body — never leaking implementation
     * details such as class names, file paths or partial stack traces to clients.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unexpected(Exception exception, HttpServletRequest request){
        log.error("Unhandled exception on {} {}", request.getMethod(), request.getRequestURI(), exception);
        return buildBody(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", request);
    }
}
