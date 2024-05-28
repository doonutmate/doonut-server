package com.doonutmate.calendar.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(title = "회원 캘린더 제목 요청")
data class CalendarNameRequest(
    @field:NotBlank(message = "캘린더 제목은 공백 일 수 없습니다")
    @field:Size(min = 1, max = 12, message = "캘린더 제목은 최소 1자에서 최대 8자입니다.")
    @Schema(title = "캘린더 제목")
    val title: String,
)
