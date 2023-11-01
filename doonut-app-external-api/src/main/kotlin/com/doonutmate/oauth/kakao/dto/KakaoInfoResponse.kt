package com.doonutmate.oauth.kakao.dto

data class KakaoInfoResponse(
    val id: Long?,
    val connected_at: String?,
    val kakao_account: KakaoAccount?,
    val properties: Properties?,
)

data class KakaoAccount(
    val profile: Profile?,
    val profile_image_needs_agreement: Boolean?,
    val profile_nickname_needs_agreement: Boolean?,
)

data class Properties(
    val nickname: String?,
    val profile_image: String?,
    val thumbnail_image: String?,
)

data class Profile(
    val is_default_image: Boolean?,
    val nickname: String?,
    val profile_image_url: String?,
    val thumbnail_image_url: String?,
)
