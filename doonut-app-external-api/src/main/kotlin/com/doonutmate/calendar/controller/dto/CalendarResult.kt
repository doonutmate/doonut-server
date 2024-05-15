package com.doonutmate.calendar.controller.dto

import org.springframework.data.domain.Slice

data class CalendarResult<T>(
    val values: Slice<T>,
)
