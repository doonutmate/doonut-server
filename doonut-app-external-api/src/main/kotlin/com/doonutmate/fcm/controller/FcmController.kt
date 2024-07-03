package com.doonutmate.fcm.controller

import com.doonutmate.fcm.client.dto.Notification
import com.doonutmate.fcm.service.FcmService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "FCM")
@RestController
class FcmController(
    val fcmService: FcmService,
) {
    @Operation(summary = "FCM 전송")
    @PostMapping("/send")
    fun pushMessage(
        @RequestBody
        req: Notification,
    ): Any {
        return fcmService.sendMessageTo(req)
    }

    @Operation(summary = "디바이스 토큰 업데이트")
    @PutMapping("/device-token")
    fun updateDeviceToken(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
        @RequestParam
        deviceToken: String,
    ) {
        fcmService.updateDeviceToken(deviceToken, memberId)
    }
}
