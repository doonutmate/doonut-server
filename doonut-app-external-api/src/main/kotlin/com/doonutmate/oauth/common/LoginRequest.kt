package com.doonutmate.oauth.common

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

@Schema(title = "oauth에서 발급 한 accessToken")
data class LoginRequest(
    @Schema(title = "액세스 토큰")
    @NotEmpty
    val accessToken: String,
)
