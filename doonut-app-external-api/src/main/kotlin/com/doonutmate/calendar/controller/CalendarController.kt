package com.doonutmate.calendar.controller

import com.doonutmate.calendar.controller.dto.CalendarNameRequest
import com.doonutmate.calendar.controller.dto.CalendarNameResponse
import com.doonutmate.calendar.controller.dto.CalendarReportRequest
import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.calendar.controller.dto.CalendarTotalCount
import com.doonutmate.calendar.service.CalendarAppService
import com.doonutmate.doonut.calendar.model.CalendarReportReason
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
        description = """ 
            1. 첫요청시 time은 null
            2. 이후 요청시 마지막 속성의 updatedAt을 time 파라미터로 사용
            3. 다음 조회가 불가능한 경우 hasNext가 false
            4. size가 null이면 10, null이 아니면 size크기만큼 조회됨
            """,
    )
    @GetMapping("")
    fun get(
        @RequestParam(required = false) time: Instant?,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
    ): CalendarResult<CalendarResponse> {
        return calendarAppService.get(time, size, memberId)
    }

    @Operation(summary = "설문 신고", description = "멤버가 설문을 신고한다.")
    @PostMapping("/reports")
    fun report(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
        @RequestBody req: CalendarReportRequest,
    ): ResponseEntity<Unit> {
        calendarAppService.report(CalendarReportReason.of(memberId, req.calendarId, req.reason))
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "캘린더 제목 조회")
    @GetMapping("/profile")
    fun getCalendarTitle(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
    ): CalendarNameResponse {
        return CalendarNameResponse(calendarAppService.getCalendar(memberId).calendarName)
    }

    @Operation(summary = "캘린더 사진기록 횟수 조회")
    @GetMapping("/total-count")
    fun getMemberTotalCount(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
    ): CalendarTotalCount {
        return CalendarTotalCount(calendarAppService.getCalendar(memberId).totalCount)
    }

    @Operation(summary = "캘린더 제목 설정")
    @PutMapping("/profile")
    fun updateCalendarTitle(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
        @Valid
        @RequestBody
        req: CalendarNameRequest,

    ): ResponseEntity<Void> {
        calendarAppService.updateCalendar(memberId, req.title)
        return ResponseEntity.ok().build()
    }

    // TODO 추후 캘린더가 복수가 될 가능성이 있을 떄, 수정해야하는 로직 (캘린더 조회, 캘린더 설정)

    // TODO 현재 전역예외처리에 @Valid 에러가 넘어가서 400으로 나온다. 이문제를 @Valid전용 전역설정을 만들어야 한다.
}
