package com.doonutmate.example.controller

import com.doonutmate.example.service.LogService
import com.doonutmate.oauth.exception.BaseException
import com.doonutmate.oauth.exception.BaseExceptionCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogApplication(
    private val logService: LogService,
) {
    @GetMapping("/log")
    fun getLog(): String {
        logService.log()
        return "console.log"
    }

    @GetMapping("/test/illegal-argument")
    fun testIllegalArgumentException(): String {
        throw IllegalArgumentException("잘못된 인자입니다.")
    }

    @GetMapping("/test/illegal-state")
    fun testIllegalStateException(): String {
        throw IllegalStateException("잘못된 상태입니다.")
    }

    @GetMapping("test/base")
    fun baseException(): String {
        throw BaseException(BaseExceptionCode.INTERNAL_SERVER_ERROR)
    }
}
