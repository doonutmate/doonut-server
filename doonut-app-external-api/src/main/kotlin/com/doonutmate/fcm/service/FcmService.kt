package com.doonutmate.fcm.service

import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.fcm.client.FcmAccessClient
import com.doonutmate.fcm.client.dto.FcmMessage
import com.doonutmate.fcm.client.dto.FcmRequest
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

    fun sendNotification(req: FcmRequest, useNickname: Boolean, useNightAlarm: Boolean) {
        val tokenList = getTokens(useNightAlarm)
        val authorization = "Bearer ${getAccessToken()}"

        tokenList.forEach { token ->
            val title = branchTitle(req.title, token, useNickname)
            val message = createMessage(token, title, req.content)
            sendMessage(authorization, message)
        }
    }

    private fun getTokens(useNightAlarm: Boolean): List<String> {
        return when (useNightAlarm) {
            true -> memberBusinessService.serviceTokenListForLateAlarm
            false -> memberBusinessService.serviceTokenList
        }
    }

    private fun getAccessToken(): String {
        val firebaseConfigPath = "firebase_key.json"
        val googleCredentials = GoogleCredentials
            .fromStream(ClassPathResource(firebaseConfigPath).inputStream)
            .createScoped(listOf("https://www.googleapis.com/auth/cloud-platform"))

        googleCredentials.refreshIfExpired()
        return googleCredentials.accessToken.tokenValue
    }

    private fun createMessage(targetToken: String, title: String, body: String): FcmMessage {
        return FcmMessage.of(false, title, body, targetToken)
    }

    private fun branchTitle(title: String, token: String, useNickname: Boolean): String {
        return when (useNickname) {
            true -> "${memberBusinessService.getMemberNameByDeviceToken(token)}님, $title"
            false -> title
        }
    }

    private fun sendMessage(authorization: String, fcmMessage: FcmMessage) {
        fcmAccessClient.sendFcmMessage(authorization, fcmMessage)
    }
}
