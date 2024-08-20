package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "알람 설정 조회 응답")
data class AlarmConfigResponse(
    @Schema(title = "서비스 알람")
    val serviceAlarm: Boolean,

    @Schema(title = "심야시간 알람")
    val lateNightAlarm: Boolean,

    @Schema(title = "마케팅 수신 동의")
    val marketingReceiveConsent: Boolean,

    @Schema(title = "마케팅 수신 동의 업데이트 시간", example = "2024.06.24")
    val marketingReceiveConsentUpdatedAt: String,
)
