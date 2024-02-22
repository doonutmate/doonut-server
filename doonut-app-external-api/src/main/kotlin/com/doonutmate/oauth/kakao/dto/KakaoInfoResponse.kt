package com.doonutmate.oauth.kakao.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "카카오 토큰을 이용해, 카카오 oauth 서버에서 반환받는 정보들을 담은 dto")
data class KakaoInfoResponse(
    // TODO("Long변환")
    val id: String,
    val connected_at: String?,
    val kakao_account: KakaoUserAccount?,
)

data class KakaoUserAccount(
    val email: String?,
    val profile: KakaoUserProfile,
)

data class KakaoUserProfile(
    val nickname: String,
    val profile_image_url: String?,
    val thumbnail_image_url: String?,
)
