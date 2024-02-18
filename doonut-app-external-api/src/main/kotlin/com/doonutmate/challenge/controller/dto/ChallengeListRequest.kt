package com.doonutmate.challenge.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(title = "Challenge 목록 조회 요청")
data class ChallengeListRequest(
    @Schema(title = "연", example = "2024")
    @NotNull
    @NotBlank
    var year: Int,

    @Schema(title = "월", example = "10")
    @NotNull
    @NotBlank
    var month: Int,
)
