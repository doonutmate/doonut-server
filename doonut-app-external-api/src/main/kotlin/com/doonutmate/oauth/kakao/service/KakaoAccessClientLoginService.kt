package com.doonutmate.oauth.kakao.service

import com.doonutmate.oauth.kakao.client.KakaoAccessClient
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import org.springframework.stereotype.Service

@Service
class KakaoAccessClientLoginService(
    private val kakaoAccessClient: KakaoAccessClient,
) {

    fun getKakaoUserId(tokenRequest: KakaoTokenRequest): TokenIdResponse {
        return kakaoAccessClient.getKakaoUserId("Bearer ${tokenRequest.accessToken}")
    }

    fun getKakaoUserInfo(tokenRequest: KakaoTokenRequest): KakaoInfoResponse {
        return kakaoAccessClient.getKakaoUserInfo("Bearer ${tokenRequest.accessToken}")
    }
}
