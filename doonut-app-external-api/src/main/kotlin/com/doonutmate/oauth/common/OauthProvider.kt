package com.doonutmate.oauth.common

interface OauthProvider<T, U> {
    fun getUserId(tokenRequest: TokenRequest): T
    fun getUserInfo(tokenRequest: TokenRequest): U
    fun signUp(tokenRequest: TokenRequest): Long
}
