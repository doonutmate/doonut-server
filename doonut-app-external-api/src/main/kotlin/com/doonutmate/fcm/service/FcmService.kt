package com.doonutmate.fcm.service

import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.fcm.client.FcmAccessClient
import com.doonutmate.fcm.client.dto.FcmMessage
import com.doonutmate.fcm.client.dto.Message
import com.doonutmate.fcm.client.dto.Notification
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class FcmService(
    private val fcmAccessClient: FcmAccessClient,
    private val memberBusinessService: MemberBusinessService,
) {
    fun updateDeviceToken(deviceToken: String, memberId: Long) {
        memberBusinessService.updateDeviceToken(deviceToken, memberId)
    }

    fun sendMessageTo(notification: Notification) {
        val tokenList = memberBusinessService.serviceAlarmList

        val authorization = "Bearer " + getAccessToken()

        for (i in tokenList) {
            fcmAccessClient.sendFcmMessage(authorization, makeMessage(i, notification.title, notification.body))
        }
    }

    private fun getAccessToken(): String {
        val firebaseConfigPath = "firbase_key.json"
        val googleCredentials = GoogleCredentials
            .fromStream(ClassPathResource(firebaseConfigPath).inputStream)
            .createScoped(listOf("https://www.googleapis.com/auth/cloud-platform"))

        googleCredentials.refreshIfExpired()
        return googleCredentials.accessToken.tokenValue
    }

    // TODO firebase 파일명 오타
    private fun makeMessage(targetToken: String, title: String, body: String): FcmMessage {
        return FcmMessage(false, Message(Notification(title, body), targetToken))
    }
}
