package com.doonutmate.oauth.kakao.dto

import com.doonutmate.doonut.member.entity.MemberEntity

data class LoginUserInfo(
    val memberEntity: MemberEntity,
    val isChecked: Boolean,
)
