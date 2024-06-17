package com.doonutmate.fcm.client.dto

data class FcmSendRequest(
    val token: String,
    val title: String,
    val body: String,
)
