package com.doonutmate.calendar.controller

import com.doonutmate.calendar.service.CalendarService
import com.doonutmate.doonut.calendar.model.Calendar
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@Tag(name = "Calendar", description = "캘린더 API")
@RequestMapping("/calendar")
class CalendarController(
    private val calendarService: CalendarService,
) {
    @Operation(summary = "캘린더 가져오기", description = "첫요청시 id와 time은 null, 그이후는 id와 time을 넣는다.")
    @GetMapping("{id}/{time}")
    fun login(@PathVariable id: Long?, @PathVariable time: Instant?): List<Calendar> {
        return calendarService.getBoards(id, time, PageRequest.of(0, 10))
    }
}
