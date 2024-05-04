package com.doonutmate.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        int httpStatus,
        String message,
        String path
) {

    public static ErrorResponse of(HttpStatus httpStatus, String message, String path) {
        return new ErrorResponse(httpStatus.value(), message, path);
    }
}
