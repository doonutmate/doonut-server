package com.doonutmate.oauth.exception

private const val APPLE_KEY_NOT_MATCHED_ERROR_MESSAGE = "애플 서버 통신 과정에서 알 수 없는 에러가 발생했습니다."

class AppleKeyNotMatchedException : RuntimeException(APPLE_KEY_NOT_MATCHED_ERROR_MESSAGE)
