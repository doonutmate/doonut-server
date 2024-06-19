package com.doonutmate.calendar.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema(title = "캘린더 조회 응답")
data class CalendarResponse(
    val id: Long,
    val totalCount: Int,
    val memberName: String,
    val calendarName: String,
    val firstUploadedAt: String,
    val lastUploadedAt: String,
    val updatedAt: Instant,
    val profileImage: String?,
)
