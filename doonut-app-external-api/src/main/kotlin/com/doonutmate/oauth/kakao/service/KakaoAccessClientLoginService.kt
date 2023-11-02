package com.doonutmate.oauth.kakao.service

import com.doonutmate.oauth.kakao.client.KakaoAccessClient
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class KakaoAccessClientLoginService(
    private val kakaoAccessClient: KakaoAccessClient,
) {

    fun getKakaoUserId(tokenRequest: KakaoTokenRequest): TokenIdResponse {
        return kakaoAccessClient.getKakaoUserId("Bearer ${tokenRequest.accessToken}")
    }
    fun getKakaoUserInfo(tokenRequest: KakaoTokenRequest): ResponseEntity<KakaoInfoResponse> {
        return kakaoAccessClient.getKakaoUserInfo("Bearer ${tokenRequest.accessToken}")
    }
}
