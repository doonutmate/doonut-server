package com.doonutmate.oauth.kakao.dto

import com.doonutmate.doonut.member.model.Member

data class LoginUserInfo(
    val member: Member,
    val isChecked: Boolean,
)
