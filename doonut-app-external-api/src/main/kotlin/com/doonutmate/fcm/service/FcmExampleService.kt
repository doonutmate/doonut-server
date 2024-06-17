package com.doonutmate.fcm.service

import com.doonutmate.fcm.client.FcmAccessClient
import com.doonutmate.fcm.client.dto.FcmMessage
import com.doonutmate.fcm.client.dto.FcmSendRequest
import com.doonutmate.fcm.client.dto.Message
import com.doonutmate.fcm.client.dto.Notification
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class FcmExampleService(
    private val fcmAccessClient: FcmAccessClient,
) {

    fun sendMessage(req: FcmSendRequest): FcmMessage {
        return fcmAccessClient.sendFcmMessage(getAccessToken(), makeMessage(req))
    }

    private fun getAccessToken(): String {
        val firebaseConfigPath = "firebase_key.json"
        val googleCredentials = GoogleCredentials
            .fromStream(ClassPathResource(firebaseConfigPath).inputStream)
            .createScoped(listOf("https://www.googleapis.com/auth/cloud-platform"))

        googleCredentials.refreshIfExpired()
        return googleCredentials.accessToken.tokenValue
    }

    private fun makeMessage(req: FcmSendRequest): FcmMessage {
        return FcmMessage(false, Message(Notification(req.title, req.body), req.token))
    }
}
