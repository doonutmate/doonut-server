package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "서비스 알람 설정 요청")
data class ServiceAlarmRequest(
    @Schema(title = "서비스 알람")
    val serviceAlarm: Boolean,
)
