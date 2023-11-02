package com.doonutmate.oauth.kakao.dto

data class KakaoInfoResponse(
    // TODO: 이거 long으로 바꿔야함
    val id: String?,
    val connected_at: String?,
    val kakao_account: KakaoAccount?,
)

data class KakaoAccount(
    val name: String?,
    val email: String?,
)
