package com.doonutmate.challenge.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(title = "Challenge 목록 조회 응답")
data class ChallengeListResponse(
    @Schema(title = "일", example = "31")
    var day: Int,

    @Schema(title = "기본 이미지 url")
    val defaultUrl: String,

    @Schema(title = "썸네일 이미지 url")
    val thumbNailUrl: String,

    @Schema(title = "업로드 시간", example = "2024-06-16T21:19:41")
    var timestamp: LocalDateTime,
)
