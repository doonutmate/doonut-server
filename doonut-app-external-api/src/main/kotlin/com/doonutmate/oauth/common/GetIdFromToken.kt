package com.doonutmate.oauth.common

import com.doonutmate.exception.BaseException
import com.doonutmate.exception.BaseExceptionCode
import com.doonutmate.oauth.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class GetIdFromToken

@Aspect
@Component
class GetIdFromTokenAspect(
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @Around("@annotation(GetIdFromToken)")
    fun authorizationToken(joinPoint: ProceedingJoinPoint): Any? {
        val request = joinPoint.args.filterIsInstance<HttpServletRequest>().firstOrNull()
            ?: throw BaseException(BaseExceptionCode.REQUEST_NOT_FOUND)

        val authorizationHeader = request.getHeader("Authorization")
            ?: throw BaseException(BaseExceptionCode.AUTHORIZATION_HEADER_NULL)

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw BaseException(BaseExceptionCode.INVALID_TOKEN_PREFIX)
        }

        val token = authorizationHeader.substring(7)
        val userId = jwtTokenProvider.getPayload(token)
        request.setAttribute("userId", userId)
        return joinPoint.proceed()
    }
}
