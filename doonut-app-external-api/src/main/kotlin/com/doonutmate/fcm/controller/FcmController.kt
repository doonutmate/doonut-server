package com.doonutmate.fcm.controller

import com.doonutmate.fcm.client.dto.FcmRequest
import com.doonutmate.fcm.service.FcmService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "FCM")
@RequestMapping("/fcm")
class FcmController(
    val fcmService: FcmService,
) {
    @Operation(
        summary = "FCM 알림 전송",
        description = """
            사용자 닉네임을 담은 알림을 전송하고 싶으면 useNickname = true,       
            심야 알림을 전송하고 싶으면 useLateAlarm = true
        """,
    )
    @PostMapping("/send")
    fun sendFcmNotification(
        @RequestBody
        req: FcmRequest,
        @RequestParam(defaultValue = "false") useNickname: Boolean,
        @RequestParam(defaultValue = "false") useLateAlarm: Boolean,
    ) {
        fcmService.sendNotification(req, useNickname, useLateAlarm)
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
