package com.doonutmate.oauth.apple.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AppleTokenRequest(
    @JsonProperty("client_id")
    val clientId: String,

    @JsonProperty("client_secret")
    val clientSecret: String,

    @JsonProperty("code")
    val code: String,

    @JsonProperty("grant_type")
    val grantType: String,

    @JsonProperty("refresh_token")
    val refreshToken: String?
)