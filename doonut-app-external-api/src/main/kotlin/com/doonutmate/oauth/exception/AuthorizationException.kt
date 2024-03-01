package com.doonutmate.oauth.exception

class AuthorizationException(baseExceptionCode: ExceptionCode) : BaseException(baseExceptionCode.httpCode, baseExceptionCode.message)
