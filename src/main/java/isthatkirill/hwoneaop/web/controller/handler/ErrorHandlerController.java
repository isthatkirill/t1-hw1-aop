package isthatkirill.hwoneaop.web.controller.handler;

import isthatkirill.hwoneaop.web.controller.handler.exception.BookAlreadyExistsException;
import isthatkirill.hwoneaop.web.controller.handler.exception.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse notValidArgumentHandle(final MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String errorMessage = errors.stream()
                .map(error -> String.format("Field: %s || error: %s || value: %s",
                        error.getField(), error.getDefaultMessage(), error.getRejectedValue()))
                .collect(Collectors.joining("\n"));
        log.error("Error: {} Description: {}", HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationHandle(final ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMessage = violations.stream()
                .map(violation -> String.format("Field: %s || error: %s || value: %s",
                        violation.getPropertyPath().toString(), violation.getMessage(), violation.getInvalidValue()))
                .collect(Collectors.joining("\n"));
        log.error("Error: {} Description: {}", HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse missingParameterHandle(final MissingServletRequestParameterException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse bookAlreadyExistsHandle(final BookAlreadyExistsException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse completionExceptionHandle(final CompletionException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getCause().getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse entityNotFoundHandle(final EntityNotFoundException e) {
        log.error("{}", e.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unexpectedErrorHandle(final Exception e) {
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
    }

}
