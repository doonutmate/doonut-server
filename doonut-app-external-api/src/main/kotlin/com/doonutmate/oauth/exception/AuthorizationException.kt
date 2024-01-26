package com.doonutmate.oauth.exception

class AuthorizationException(baseExceptionCode: BaseExceptionCode) : BaseException(baseExceptionCode.httpCode, baseExceptionCode.message)
