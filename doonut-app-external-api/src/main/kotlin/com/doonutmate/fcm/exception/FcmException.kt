package com.doonutmate.fcm.exception

import com.doonutmate.exception.BaseException
import com.doonutmate.exception.ErrorCode
import org.springframework.http.HttpStatus

class FcmException(errorCode: ErrorCode?, errorMessage: String) :
    BaseException(HttpStatus.BAD_REQUEST, errorCode, errorMessage) {
    constructor(errorMessage: String) : this(null, errorMessage)
}
