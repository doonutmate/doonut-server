package com.doonutmate.oauth.common

abstract class OauthProvider<T, U> {
    abstract fun getUserId(tokenRequest: TokenRequest): T
    abstract fun getUserInfo(tokenRequest: TokenRequest): U
}
