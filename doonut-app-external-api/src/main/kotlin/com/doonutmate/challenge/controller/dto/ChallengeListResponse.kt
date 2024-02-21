package com.doonutmate.challenge.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Challenge 목록 조회 응답")
data class ChallengeListResponse(
    @Schema(title = "일", example = "31")
    var day: String,

    @Schema(title = "이미지 url")
    val imageUrl: String,
)
