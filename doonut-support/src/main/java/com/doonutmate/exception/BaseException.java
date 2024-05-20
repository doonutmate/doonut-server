package com.doonutmate.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorCode errorCode;
    private final String message;

    public BaseException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = null;
        this.message = message;
    }

    public BaseException(HttpStatus httpStatus, ErrorCode errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
