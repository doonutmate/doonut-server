package com.doonutmate.calendar.exception

import com.doonutmate.exception.BaseException
import com.doonutmate.exception.ErrorCode
import org.springframework.http.HttpStatus

class CalendarException(errorCode: ErrorCode?, errorMessage: String) : BaseException(HttpStatus.BAD_REQUEST, errorCode, errorMessage) {
    constructor(errorMessage: String) : this(null, errorMessage)
}
