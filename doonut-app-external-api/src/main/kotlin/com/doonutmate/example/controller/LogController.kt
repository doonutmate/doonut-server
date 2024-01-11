package com.doonutmate.example.controller

import com.doonutmate.example.service.LogService
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
}
