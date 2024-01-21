package com.doonutmate.oauth.exception

data class ApiResponse<T>(
    val message: String? = "",
    val body: T? = null,
    val trace: StackTraceElement? = null,
) {
    companion object {
        fun error(message: String?): ApiResponse<Unit> = ApiResponse(message = message)
        fun error(message: String?, trace: StackTraceElement): ApiResponse<Unit> = ApiResponse(message = message, null, trace)

        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(body = body)
    }
}
