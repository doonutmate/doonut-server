package com.doonutmate.oauth.configuration

import com.doonutmate.oauth.JwtTokenProvider
import com.doonutmate.oauth.exception.BaseException
import com.doonutmate.oauth.exception.BaseExceptionCode
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthorizationArgumentResolver(
    private val jwtTokenProvider: JwtTokenProvider,
) : HandlerMethodArgumentResolver {
    companion object {
        const val BEARER_PREFIX_LEN = 7
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(Authorization::class.java) != null &&
            parameter.parameterType == String::class.java }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) as HttpServletRequest

        val authorizationHeader = request.getHeader("Authorization")
            ?: throw BaseException(BaseExceptionCode.AUTHORIZATION_HEADER_NULL)

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw BaseException(BaseExceptionCode.INVALID_TOKEN_PREFIX)
        }

        val token = authorizationHeader.substring(BEARER_PREFIX_LEN)
        return jwtTokenProvider.getPayload(token)
    }
}
