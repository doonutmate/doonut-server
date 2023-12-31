package com.doonutmate.oauth.exception

import org.springframework.http.HttpStatus

enum class BaseExceptionCode(val httpStatusCode: Int, val message: String) {

    AUTHORIZATION_HEADER_NULL(HttpStatus.BAD_REQUEST.value(), "인증 헤더가 null입니다."),

    INVALID_TOKEN_PREFIX(HttpStatus.UNAUTHORIZED.value(), "Bearer값이 아닙니다"),
}
