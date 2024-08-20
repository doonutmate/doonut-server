package com.doonutmate.fcm.client.dto

data class FcmMessage(
    val validateOnly: Boolean,
    val message: Message,
) {
    data class Message(
        val notification: Notification,
        val token: String,
    )

    data class Notification(
        var title: String,
        val body: String,
    )

    companion object {
        fun of(validate: Boolean, title: String, body: String, token: String): FcmMessage {
            val notification = Notification(title, body)
            val message = Message(notification, token)
            return FcmMessage(validate, message)
        }
    }
}
