package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "마이페이지 조회 응답")
data class MyPageResponse(
    @Schema(title = "닉네임")
    val nickname: String,

    @Schema(title = "프로필 이미지 url")
    val profileImageUrl: String?,
)
