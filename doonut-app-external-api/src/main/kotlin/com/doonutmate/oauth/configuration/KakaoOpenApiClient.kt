package com.doonutmate.oauth.configuration

import com.doonutmate.oauth.dto.TokenIdResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import reactor.core.publisher.Mono

@HttpExchange("https://kapi.kakao.com")
interface KakaoOpenApiClient {
    @PostExchange("/v2/user/me")
    fun getKakaoUserId(
        @RequestHeader("Authorization") authorization: String,
    ): TokenIdResponse

    @PostExchange("/v2/user/me")
    fun getKakaoUserInfo2(
        @RequestHeader("Authorization") authorization: String,
    ): Mono<ResponseEntity<String>>
}
