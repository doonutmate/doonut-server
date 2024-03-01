package com.doonutmate.oauth.configuration

import com.doonutmate.oauth.JwtTokenProvider
import com.doonutmate.oauth.exception.AuthorizationException
import com.doonutmate.oauth.exception.ExceptionCode
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

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(Authorization::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) as HttpServletRequest

        val authorizationHeader = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION)
            ?: throw AuthorizationException(ExceptionCode.AUTHORIZATION_HEADER_NULL)

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw AuthorizationException(ExceptionCode.INVALID_TOKEN_PREFIX)
        }

        val token = authorizationHeader.substring(BEARER_PREFIX_LEN)
        return jwtTokenProvider.getPayload(token).toLong()
    }

    companion object {
        const val BEARER_PREFIX_LEN = 7
    }
}
