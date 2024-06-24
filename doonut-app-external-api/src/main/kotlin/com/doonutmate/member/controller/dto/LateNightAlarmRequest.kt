package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "심야시간 알람 설정 요청")
data class LateNightAlarmRequest(
    @Schema(title = "심야시간 알람")
    val lateNightAlarm: Boolean,
)
