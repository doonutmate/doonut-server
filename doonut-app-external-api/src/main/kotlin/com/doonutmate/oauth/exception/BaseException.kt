package com.doonutmate.oauth.exception

import org.springframework.http.HttpStatus

open class BaseException(val httpStatus: HttpStatus, override val message: String) : RuntimeException()
