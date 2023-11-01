package com.doonutmate.oauth.kakao.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

@Schema(title = "카카오에서 발급 된 accessToken")
data class KakaoTokenRequest(
    @Schema(title = "토큰", example = "")
    @NotEmpty
    val accessToken: String
)
