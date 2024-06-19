package com.doonutmate.fcm.client

import com.doonutmate.fcm.client.dto.FcmMessage
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
interface FcmAccessClient {

    @PostExchange("\${fcm.url}")
    fun sendFcmMessage(
        @RequestHeader("Authorization") authorization: String,
        @RequestBody message: FcmMessage,
    ): FcmMessage
}
