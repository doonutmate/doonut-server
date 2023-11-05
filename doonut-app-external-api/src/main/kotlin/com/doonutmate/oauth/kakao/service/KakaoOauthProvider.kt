package com.doonutmate.oauth.kakao.service

import com.doonutmate.oauth.kakao.client.KakaoAccessClient
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import com.doonutmate.oauth.kakao.util.OauthProvider
import org.springframework.stereotype.Service

@Service
class KakaoOauthProvider(
    private val kakaoAccessClient: KakaoAccessClient,
) : OauthProvider() {

    override fun getUserId(tokenRequest: KakaoTokenRequest): TokenIdResponse {
        return kakaoAccessClient.getKakaoUserId("Bearer ${tokenRequest.accessToken}")
    }

    override fun getUserInfo(tokenRequest: KakaoTokenRequest): KakaoInfoResponse {
        return kakaoAccessClient.getKakaoUserInfo("Bearer ${tokenRequest.accessToken}")
    }
}
