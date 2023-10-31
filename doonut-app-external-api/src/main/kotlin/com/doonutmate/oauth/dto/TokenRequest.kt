package com.doonutmate.oauth.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

@Schema(title = "토큰을 담고 있는 요청 DTO")
data class TokenRequest(
    @Schema(title = "토큰", example = "")
    @NotEmpty
    val accessToken: String
)
