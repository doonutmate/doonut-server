package com.doonutmate.calendar.controller

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.calendar.service.CalendarAppService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@Tag(name = "Calendar", description = "캘린더 API")
@RequestMapping("/calendars")
class CalendarController(
    private val calendarAppService: CalendarAppService,
) {
    @Operation(
        summary = "커뮤니티 캘린더 모아보기 ",
        description = "첫요청시 time은 null, 그이후는 이전 조회 캘린더의 마지막 updatedAt을 넣는다. 다음 조회가 불가능한 경우 last가 true",
    )
    @GetMapping("/{size}")
    fun get(
        @RequestParam(required = false) time: Instant?,
        @PathVariable size: Int,
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
    ): CalendarResult<CalendarResponse> {
        return calendarAppService.get(time, PageRequest.of(0, size))
    }
}
