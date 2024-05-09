package com.doonutmate.calendar.controller

import com.doonutmate.calendar.service.CalendarService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Calendar", description = "캘린더 API")
@RequestMapping("/calendar")
class CalendarController(
    private val calendarService: CalendarService
) {
}