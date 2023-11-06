package com.doonutmate.oauth.common

import com.doonutmate.oauth.kakao.dto.TokenRequest

abstract class OauthProvider<T, U> {
    abstract fun getUserId(tokenRequest: TokenRequest): T
    abstract fun getUserInfo(tokenRequest: TokenRequest): U
}
