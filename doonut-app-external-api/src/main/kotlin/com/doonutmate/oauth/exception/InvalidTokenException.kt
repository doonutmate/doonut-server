package com.doonutmate.oauth.exception

private const val INVALID_TOKEN_ERROR_MESSAGE = "유효하지 않은 토큰입니다."

class InvalidTokenException : RuntimeException(INVALID_TOKEN_ERROR_MESSAGE)
