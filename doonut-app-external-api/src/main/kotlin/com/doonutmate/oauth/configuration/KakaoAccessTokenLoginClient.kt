package com.doonutmate.oauth.configuration

import com.doonutmate.oauth.dto.TokenIdResponse
import org.springframework.stereotype.Component

@Component
class KakaoAccessTokenLoginClient(
    private val kakaoAuthClient: KakaoOpenApiClient,
) {
    fun login(token: String): TokenIdResponse =
        kakaoAuthClient.getKakaoUserInfo("Bearer $token").block()
            ?: throw Exception()
}
