package com.doonutmate.oauth.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.oauth.controller.dto.LoginRequest

interface OauthProvider<T, U> {
    fun getUserId(loginRequest: LoginRequest): T
    fun getUserInfo(loginRequest: LoginRequest): U
    fun signUp(loginRequest: LoginRequest): Member
}
