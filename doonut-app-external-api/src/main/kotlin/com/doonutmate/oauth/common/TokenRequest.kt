package com.doonutmate.oauth.common

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

@Schema(title = "oauth accessToken")
data class TokenRequest(
    @Schema(title = "토큰", example = "")
    @NotEmpty
    val accessToken: String,
)
