package com.doonutmate.oauth.kakao.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "카카오 토큰을 이용해, 카카오 oauth 서버에서 반환받는 정보들을 담은 dto")
data class KakaoInfoResponse(
    val id: String,
    val connected_at: String?,
    val kakao_account: KakaoAccount?,
)

data class KakaoAccount(
    val name: String?,
    val email: String?,
)
