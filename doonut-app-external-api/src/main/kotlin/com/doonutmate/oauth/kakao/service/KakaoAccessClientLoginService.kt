package com.doonutmate.oauth.kakao.service

import com.doonutmate.oauth.kakao.client.KakaoAccessClient
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KakaoAccessClientLoginService(
    private val kakaoAuthClient: KakaoAccessClient,
) {

    fun getKakaoUserId(tokenRequest: KakaoTokenRequest): TokenIdResponse {
        return kakaoAuthClient.getKakaoUserId("Bearer ${tokenRequest.accessToken}")
    }
    fun getKakaoUserInfo(tokenRequest: KakaoTokenRequest): ResponseEntity<KakaoInfoResponse> {
        return kakaoAuthClient.getKakaoUserInfo2("Bearer ${tokenRequest.accessToken}")
    }
}
