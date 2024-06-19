package com.doonutmate.fcm.client.dto

data class FcmMessage(
    val validateOnly: Boolean,
    val message: Message,
)
