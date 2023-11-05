package com.doonutmate.oauth.externalOauth

import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import com.doonutmate.oauth.kakao.dto.TokenRequest

abstract class OauthProvider {
    abstract fun getUserId(tokenRequest: TokenRequest): TokenIdResponse
    abstract fun getUserInfo(tokenRequest: TokenRequest): KakaoInfoResponse
}
