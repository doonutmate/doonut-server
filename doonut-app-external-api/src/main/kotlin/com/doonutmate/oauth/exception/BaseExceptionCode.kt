package com.doonutmate.oauth.exception

import org.springframework.http.HttpStatus

enum class BaseExceptionCode(private val httpStatusCode: HttpStatus, private val message: String) {

    // 400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    // TODO 추후 에러 추가

    AUTHORIZATION_HEADER_NULL(HttpStatus.BAD_REQUEST, "인증 헤더가 null입니다."),

    INVALID_TOKEN_PREFIX(HttpStatus.UNAUTHORIZED, "Bearer값이 아닙니다"),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다. 서버 팀에 연락주세요!"),
}
