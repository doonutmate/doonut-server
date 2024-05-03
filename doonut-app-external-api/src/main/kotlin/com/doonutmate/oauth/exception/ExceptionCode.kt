package com.doonutmate.oauth.exception

import org.springframework.http.HttpStatus

enum class ExceptionCode(val httpCode: HttpStatus, val message: String) {

    /**
     * 400 BAD_REQUEST: 잘못된 요청
     *
     * @see org.springframework.http.HttpStatus.BAD_REQUEST
     */
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    AUTHORIZATION_HEADER_NULL(HttpStatus.BAD_REQUEST, "인증 헤더가 null입니다."),
    INVALID_TOKEN_PREFIX(HttpStatus.UNAUTHORIZED, "Bearer값이 아닙니다"),

    /**
     * 500 INTERNAL SERVER ERROR: 내부 서버 오류
     *
     * @see org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),
    NOT_MATCHED_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "Apple JWT 값의 alg, kid 정보가 올바르지 않습니다.")
}
