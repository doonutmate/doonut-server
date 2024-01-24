package com.doonutmate.oauth.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    /*
 * Custom Exception
 */

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleBadRequestException(exception: RuntimeException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorMessage = exception.message ?: "Unknown error"
        val errorLocation = exception.stackTrace.firstOrNull()

        logger.error("Error occurred: $errorMessage , Location: $errorLocation")
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message ?: "Unknown error",
            request.requestURI,
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

//    @ExceptionHandler(BaseException::class)
//    fun handleBaseException(exception: BaseException): ResponseEntity<ApiResponse<Unit>> {
//        logger.warn(exception.message, exception)
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(ApiResponse.error(exception.message))
//    }
//
//    @ExceptionHandler(RuntimeException::class)
//    fun handleRuntimeException(exception: RuntimeException): ResponseEntity<ApiResponse<Unit>> {
//        logger.warn("RuntimeException: ${exception.message}", exception)
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(ApiResponse.error(exception.message))
//    }
//
//    @ExceptionHandler(Exception::class)
//    fun handleGlobalException(exception: Exception): ResponseEntity<ApiResponse<Unit>> {
//        logger.error("예상하지 못한 에러가 발생했습니다. ${exception.message}", exception)
//        logger.error(exception.stackTrace[0].toString())
//        return ResponseEntity
//            .status(HttpStatus.INTERNAL_SERVER_ERROR)
//            .body(ApiResponse.error(exception.message, exception.stackTrace[0]))
//    }
}
