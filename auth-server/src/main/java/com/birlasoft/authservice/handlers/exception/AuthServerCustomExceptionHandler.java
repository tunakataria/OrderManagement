package com.birlasoft.authservice.handlers.exception;

import com.birlasoft.authservice.exceptions.ApiError;
import com.birlasoft.authservice.exceptions.ProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.Instant;


@ControllerAdvice
public class AuthServerCustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ProcessingException.class})
    ResponseEntity<?> handleExceptions(RuntimeException ex, WebRequest request) {
        if (ex instanceof ProcessingException) {
            return generateErrorResponse(((ProcessingException) ex).getErrorCodeAndMessage().getHttpCode(),
                    getApiError(((ProcessingException) ex).getErrorCodeAndMessage().getErrorCode(),
                            ((ProcessingException) ex).getErrorCodeAndMessage().getErrorMessage(),
                            ((ServletWebRequest) request
                            ).getRequest().getRequestURI()));
        }
        return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> generateErrorResponse(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<Object> generateErrorResponse(int status, Object body) {
        return ResponseEntity.status(status).body(body);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        final StringBuilder errorMessageBuilder = new StringBuilder();
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex;
            cve.getConstraintViolations().stream().forEach(constraintViolation ->
                    errorMessageBuilder.append(constraintViolation.getRootBean() + "must not be" + constraintViolation.getInvalidValue()));
            return generateErrorResponse(400, getApiError("400", errorMessageBuilder.toString(), request.getContextPath()));
        }
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manv = (MethodArgumentNotValidException) ex;
            manv.getBindingResult().getFieldErrors().stream().forEach(fieldError -> {
                errorMessageBuilder.append(fieldError.getField()).append(" may not be ").append(fieldError.getRejectedValue()).append(System.getProperty("line.separator"));
            });
            return generateErrorResponse(HttpStatus.BAD_REQUEST,
                    getApiError("400", errorMessageBuilder.toString(), ((ServletWebRequest) request).getRequest().getRequestURI()));
        }
        return new ResponseEntity("Internal Server Error", null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private ApiError getApiError(String errorCode, String errorMessage, String contextPath) {
        ApiError error = ApiError.builder().errorCode(errorCode).errorMessage(errorMessage)
                .timeStamp(Instant.now()).requestPath(contextPath).build();
        return error;
    }

    private enum StatusCode {
        INVALID_USER(502),
        INVALID_ACTIVITY(502),
        BAD_REQUEST(400);
        int status;

        public int getStatus() {
            return status;
        }

        StatusCode(int status) {
            this.status = status;
        }
    }
}
