package com.doonutmate.calendar.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "회원 캘린더 제목 응답")
data class CalendarNameResponse(
    @Schema(title = "캘린더 제목")
    val title: String,
)

// FIXME CalendarNameResponse 와 CalendarNameRequest 가 같은 형태이지만 추후, 요청 응답 값이 달라질것같아 DTO 분리
