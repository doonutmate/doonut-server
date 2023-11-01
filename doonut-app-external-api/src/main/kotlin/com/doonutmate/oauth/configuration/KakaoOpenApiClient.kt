package com.doonutmate.oauth.configuration

import com.doonutmate.oauth.dto.TokenIdResponse
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.PostExchange
import reactor.core.publisher.Mono

interface KakaoOpenApiClient {
    @PostExchange("/v2/user/me")
    fun getKakaoUserInfo(
        @RequestHeader("Authorization") authorization: String,
    ): Mono<TokenIdResponse>
}