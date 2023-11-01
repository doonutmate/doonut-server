package com.doonutmate.oauth.dto

data class TokenInfoResponse(
    val id: Long,
    val connected_at: String,
    val kakao_account: String,
)
