package com.doonutmate.fcm.controller

import com.doonutmate.fcm.client.dto.FcmSendRequest
import com.doonutmate.fcm.service.FcmExampleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FcmExampleController(
    val fcmExampleService: FcmExampleService,
) {
    @PostMapping("/send")
    fun pushMessage(
        @RequestBody
        req: FcmSendRequest,
    ): Any {
        return fcmExampleService.sendMessage(req)
    }
}
