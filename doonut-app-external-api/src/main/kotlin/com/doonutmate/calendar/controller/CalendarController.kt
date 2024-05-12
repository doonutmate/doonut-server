package com.doonutmate.calendar.controller

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.calendar.service.CalendarService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@Tag(name = "Calendar", description = "캘린더 API")
@RequestMapping("/calendar")
class CalendarController(
    private val calendarService: CalendarService,
) {
    @Operation(summary = "캘린더 가져오기", description = "첫요청시 id,time은 null, 그이후는 이전 조회 캘린더의 마지막 id와 updatedAt을 넣는다. 다음 조회가 불가능한 경우 hasNext가 false")
    @GetMapping("")
    fun login(
        @RequestParam(required = false) id: Long?,
        @RequestParam(required = false) time: Instant?,
    ): CalendarResult<CalendarResponse> {
        return calendarService.get(id, time, PageRequest.of(0, 10))
    }
}
