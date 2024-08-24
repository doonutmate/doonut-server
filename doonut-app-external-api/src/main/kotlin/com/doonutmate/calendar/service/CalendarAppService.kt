package com.doonutmate.calendar.service

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.calendar.exception.CalendarException
import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.calendar.model.CalendarReportReason
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import com.doonutmate.doonut.calendar.service.CalendarReportReasonBusinessService
import com.doonutmate.exception.ErrorCode
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import java.time.Instant

private val log = KotlinLogging.logger {}

@Service
class CalendarAppService(
    private val calendarBusinessService: CalendarBusinessService,
    private val calendarFacadeService: CalendarFacadeService,
    private val reportReasonBusinessService: CalendarReportReasonBusinessService,
) {

    fun get(time: Instant?, size: Int, memberId: Long): CalendarResult<CalendarResponse> {
        val calendar = calendarBusinessService.getByMemberId(memberId)
            ?: throw CalendarException("캘린더를 찾을 수 없습니다.")

        if (calendar.calendarName.isNullOrBlank() || calendar.totalCount < COMMUNITY_ACCESS_MINIMUM_COUNT) {
            log.error { "커뮤니티 에러!!! memberId: $memberId, calendarName: ${calendar.calendarName}, totalCount: ${calendar.totalCount} " }
            throw CalendarException(ErrorCode.COMMUNITY_NOT_ACCESSIBLE, "커뮤니티는 캘린더명을 설정하고, 3개 이상의 기록이 있어야 확인할 수 있습니다.")
        }
        log.error { "[커뮤니티 정상 조회] memberId: $memberId, calendarName: ${calendar.calendarName}, totalCount: ${calendar.totalCount} " }

        val calendars: Slice<CalendarResponse> = getBoards(time, PageRequest.of(0, size), memberId)
        return CalendarResult(calendars.toList(), calendars.hasNext())
    }

    private fun getBoards(time: Instant?, page: Pageable, memberId: Long): Slice<CalendarResponse> {
        return calendarFacadeService.convertToList(calendarBusinessService.findCalendars(page, time, memberId))
    }

    fun getCalendar(memberId: Long): Calendar {
        calendarBusinessService.getByMemberId(memberId)
            ?: throw CalendarException("캘린더를 찾을 수 없습니다.")

        return calendarBusinessService.getByMemberId(memberId)
    }

    fun updateCalendar(memberId: Long, title: String) {
        calendarBusinessService.updateCalendarName(memberId, title)
    }

    fun report(reportReason: CalendarReportReason): Long {
        validateExistsCalendar(reportReason.calendarId)
        return reportReasonBusinessService.create(reportReason)
    }

    private fun validateExistsCalendar(calendarId: Long) {
        calendarBusinessService.get(calendarId)
            ?: throw CalendarException("캘린더를 찾을 수 없습니다. calendarId: $calendarId")
    }

    companion object {
        private const val COMMUNITY_ACCESS_MINIMUM_COUNT = 3
    }
}
