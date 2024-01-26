package com.doonutmate.oauth.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

private const val UNKNOWN_ERROR = "Unknown error"

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorMessage = getErrorMessage(exception)
        val errorLocation = getErrorLocation(exception)

        logger.warn("BaseException occurred: $errorMessage , Location: $errorLocation")
        return createErrorResponse(exception.httpStatus, errorMessage, request)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleBadRequestException(exception: RuntimeException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorMessage = getErrorMessage(exception)
        val errorLocation = getErrorLocation(exception)

        logger.error("RuntimeException occurred: $errorMessage , Location: $errorLocation")
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, request)
    }

    @ExceptionHandler(Exception::class)
    fun handleBadRequestException(exception: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorMessage = getErrorMessage(exception)
        val errorLocation = getErrorLocation(exception)

        logger.error("Exception occurred: $errorMessage , Location: $errorLocation")
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, request)
    }

    private fun getErrorMessage(exception: Exception) = exception.message ?: UNKNOWN_ERROR

    private fun getErrorLocation(exception: Exception) = exception.stackTrace.firstOrNull()

    private fun createErrorResponse(
        httpStatus: HttpStatus,
        errorMessage: String,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            httpStatus,
            errorMessage,
            request.requestURI,
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }
}
