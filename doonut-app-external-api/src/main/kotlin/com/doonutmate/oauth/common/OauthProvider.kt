package com.doonutmate.oauth.common

import com.doonutmate.doonut.member.model.Member

interface OauthProvider<T, U> {
    fun getUserId(tokenRequest: TokenRequest): T
    fun getUserInfo(tokenRequest: TokenRequest): U
    fun signUp(tokenRequest: TokenRequest): Member
}
