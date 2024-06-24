package com.doonutmate.member.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "마케팅 수신 동의 요청")
data class MarketingReceiveConsentRequest(
    @Schema(title = "마케팅 수신 동의")
    val marketingReceiveConsent: Boolean,
)
