package com.doonutmate.calendar.controller.dto

data class CalendarResult<T>(
    val values: List<T>,
    val hasNext: Boolean,
)
