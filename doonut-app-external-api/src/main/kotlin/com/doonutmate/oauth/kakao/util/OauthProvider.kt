package com.doonutmate.oauth.kakao.util

import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse

abstract class OauthProvider {
    abstract fun getUserId(tokenRequest: KakaoTokenRequest): TokenIdResponse
    abstract fun getUserInfo(tokenRequest: KakaoTokenRequest): KakaoInfoResponse
}
