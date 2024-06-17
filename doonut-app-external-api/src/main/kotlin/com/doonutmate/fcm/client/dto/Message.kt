package com.doonutmate.fcm.client.dto

data class Message(
    val notification: Notification,
    val token: String,
)
