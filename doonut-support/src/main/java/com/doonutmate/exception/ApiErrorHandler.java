package com.doonutmate.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    public static final String UNKNOWN_ERROR_MESSAGE = "Unknown Error";

    @ExceptionHandler({BaseException.class})
    public final ResponseEntity<ErrorResponse> handleBaseException(BaseException exception, HttpServletRequest request) {
        var errorMessage = getErrorMessage(exception);
        var errorLocation = getErrorLocation(exception);

        log.error("BaseException occurred: {} , Location: {}", errorMessage, errorLocation);
        return create4xxErrorResponse(exception.getHttpStatus(), errorMessage, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception, HttpServletRequest request) {
        var errorMessage = getErrorMessage(exception);
        var errorLocation = getErrorLocation(exception);

        log.error("RuntimeException occurred: {} , Location: {}", errorMessage, errorLocation);
        return create5xxErrorResponse(errorMessage, request);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ErrorResponse> handleException(Exception exception, HttpServletRequest request) {
        var errorMessage = getErrorMessage(exception);
        var errorLocation = getErrorLocation(exception);

        log.error("Exception occurred: {} , Location: {}", errorMessage, errorLocation);
        return create5xxErrorResponse(errorMessage, request);
    }

    private String getErrorMessage(Exception exception) {
        if (exception.getMessage() == null) {
            return UNKNOWN_ERROR_MESSAGE;
        }
        return exception.getMessage();
    }

    private StackTraceElement getErrorLocation(Exception exception) {
        return exception.getStackTrace()[0];
    }

    private ResponseEntity<ErrorResponse> create4xxErrorResponse(HttpStatus httpStatus, String errorMessage, HttpServletRequest request) {
        var errorResponse = ErrorResponse.of(httpStatus, errorMessage, request.getRequestURI());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ResponseEntity<ErrorResponse> create5xxErrorResponse(String errorMessage, HttpServletRequest request) {
        var errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, request.getRequestURI());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
