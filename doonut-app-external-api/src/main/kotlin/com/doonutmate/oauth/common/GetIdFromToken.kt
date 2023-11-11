package com.doonutmate.oauth.common

import com.doonutmate.oauth.JwtTokenProvider
import com.doonutmate.oauth.exception.BaseExceptionCode
import com.doonutmate.oauth.exception.InvalidTokenException
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.JoinPoint
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
    fun beforeMethod(joinPoint: JoinPoint) {
        val request = (
            joinPoint.args.filterIsInstance<HttpServletRequest>().firstOrNull()
                ?: throw InvalidTokenException(BaseExceptionCode.REQUEST_NOT_FOUND)
            )
        val authorizationHeader = request.getHeader("Authorization")
            ?: throw InvalidTokenException(BaseExceptionCode.AUTHORIZATION_HEADER_NULL)

        val token = authorizationHeader.substring(7)

        // "Bearer <토큰>" 형태라고 가정할 때
        val userId = jwtTokenProvider.getPayload(token)

        // 이제 토큰에서 추출한 사용자 ID를 가지고 있습니다. 여기서 추가 작업을 수행할 수 있습니다.
    }
}
