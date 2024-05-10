package com.doonutmate.oauth.exception

import com.doonutmate.exception.BaseException
import com.doonutmate.exception.ExceptionCode

class AuthorizationException(baseExceptionCode: ExceptionCode) : BaseException(baseExceptionCode.httpStatus, baseExceptionCode.message)
