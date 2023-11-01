package com.doonutmate.oauth.configuration

import com.doonutmate.oauth.dto.TokenIdResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class KakaoAccessTokenLoginClient(
    private val kakaoAuthClient: KakaoOpenApiClient,
) {

    fun getKakaoUserId(token: String): TokenIdResponse {
        return kakaoAuthClient.getKakaoUserId("Bearer $token")
    }
    fun getKakaoUserInfo(token: String): Mono<ResponseEntity<String>> {
        return kakaoAuthClient.getKakaoUserInfo2("Bearer $token")
    }
}


