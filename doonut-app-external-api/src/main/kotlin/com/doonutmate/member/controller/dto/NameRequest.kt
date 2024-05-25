package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "회원이름 요청")
data class NameRequest(
    @Schema(title = "닉네임")
    val nickname: String
)
