package com.doonutmate.oauth.common

import com.doonutmate.exception.BaseException
import com.doonutmate.exception.BaseExceptionCode
import com.doonutmate.oauth.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GetIdFromToken

@Aspect
@Component
class TokenAuthenticationAspect(
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @Before("@annotation(GetIdFromToken)")
    fun checkAuth(joinPoint: ProceedingJoinPoint): String {
        val request = (joinPoint.args.find { it is HttpServletRequest } as? HttpServletRequest)
            ?: throw BaseException(BaseExceptionCode.REQUEST_NOT_FOUND)

        val authorizationHeader = request.getHeader("Authorization")
            ?: throw BaseException(BaseExceptionCode.AUTHORIZATION_HEADER_NULL)

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw BaseException(BaseExceptionCode.INVALID_TOKEN_PREFIX)
        }

        val token = authorizationHeader.substring(7)
        return jwtTokenProvider.getPayload(token)
    }
}
