package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(title = "회원 닉네임 요청")
data class NameRequest(
    @Schema(title = "닉네임")
    @NotBlank(message = "닉네임은 공백 일 수 없습니다")
    @Size(min = 1, max = 8, message = "닉네임은 최소 1자에서 최대 8자입니다.")
    val nickname: String
)
