package com.doonutmate.oauth.kakao.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "카카오 토큰에서 id값 만을 받아오기 위한 dto")
data class KakaoTokenIdResponse(
    @Schema(title = "카카오 유저 id", example = "")
    val id: Long,
)
