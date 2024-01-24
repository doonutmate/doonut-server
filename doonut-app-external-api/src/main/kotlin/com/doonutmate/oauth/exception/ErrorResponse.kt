package com.doonutmate.oauth.exception

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
)

// val errorResponse = ErrorResponse(
//    timestamp = LocalDateTime.now(),
//    status = HttpStatus.BAD_REQUEST.value(),
//    error = "Bad Request",
//    message = exception.message,
//    path = request.requestURI,
// )
