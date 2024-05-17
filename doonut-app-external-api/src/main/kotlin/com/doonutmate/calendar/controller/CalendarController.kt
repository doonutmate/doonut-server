package com.doonutmate.calendar.controller

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.calendar.service.CalendarAppService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
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
        description = "1. 첫요청시 time은 null \n" +
            "2. 이후 요청시 마지막 속성의 updatedAt을 time 파라미터로 사용\n" +
            "3. 다음 조회가 불가능한 경우 hasNext가 false\n" +
            "4. size가 null이면 10, null이 아니면 size크기만큼 조회됨",
    )
    @GetMapping("")
    fun get(
        @RequestParam(required = false) time: Instant?,
        @RequestParam size: Int?,
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
    ): CalendarResult<CalendarResponse> {
        return calendarAppService.get(time, size)
    }
}
