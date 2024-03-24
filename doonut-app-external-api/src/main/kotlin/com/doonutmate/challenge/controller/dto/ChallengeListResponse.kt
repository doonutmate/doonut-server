package com.doonutmate.challenge.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Challenge 목록 조회 응답")
data class ChallengeListResponse(
    @Schema(title = "일", example = "31")
    var day: Int,

    @Schema(title = "기본 이미지 url")
    val defaultUrl: String,

    @Schema(title = "썸네일 이미지 url")
    val thumbNailUrl: String,
)
