package com.doonutmate.oauth.exception

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
) {
    constructor(httpStatus: HttpStatus, message: String, path: String) : this(httpStatus.value(), httpStatus.name, message, path)
}
