package com.doonutmate.oauth.common

import com.doonutmate.doonut.member.model.Member

interface OauthProvider<T, U> {
    fun getUserId(loginRequest: LoginRequest): T
    fun getUserInfo(loginRequest: LoginRequest): U
    fun signUp(loginRequest: LoginRequest): Member
}
