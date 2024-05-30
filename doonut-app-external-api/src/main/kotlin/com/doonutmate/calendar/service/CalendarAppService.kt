package com.doonutmate.calendar.service

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.calendar.exception.CalendarException
import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.calendar.model.CalendarReportReason
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import com.doonutmate.doonut.calendar.service.CalendarReportReasonBusinessService
import com.doonutmate.exception.ErrorCode
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CalendarAppService(
    private val calendarBusinessService: CalendarBusinessService,
    private val calendarFacadeService: CalendarFacadeService,
    private val reportReasonBusinessService: CalendarReportReasonBusinessService,
) {

    fun get(time: Instant?, size: Int, memberId: Long): CalendarResult<CalendarResponse> {
        validate(memberId)

        val calendars: Slice<CalendarResponse> = getBoards(time, PageRequest.of(0, size), memberId)
        return CalendarResult(calendars.toList(), calendars.hasNext())
    }

    private fun validate(memberId: Long) {
        val calendar = calendarBusinessService.getByMemberId(memberId)
            ?: throw CalendarException("캘린더를 찾을 수 없습니다. memberId: $memberId")

        if (calendar.calendarName.isNullOrBlank() || calendar.totalCount < COMMUNITY_ACCESS_MINIMUM_COUNT) {
            throw CalendarException(ErrorCode.COMMUNITY_NOT_ACCESSIBLE, "커뮤니티는 캘린더명을 설정하고, 3개 이상의 기록이 있어야 확인할 수 있습니다.")
        }
    }

    private fun getBoards(time: Instant?, page: Pageable, memberId: Long): Slice<CalendarResponse> {
        return calendarFacadeService.convertToList(calendarBusinessService.findCalendars(page, time, memberId))
    }

    fun getCalendar(memberId: Long): Calendar {
        validate(memberId)
        return calendarBusinessService.getByMemberId(memberId)
    }

    fun updateCalendar(memberId: Long, title: String): Boolean {
        try {
            val updateCount = calendarBusinessService.updateCalendarName(memberId, title)
            return updateCount > NOT_UPDATED
        } catch (e: CalendarException) {
            throw CalendarException(ErrorCode.NOT_UPDATED, "캘린더가 업데이트되지 않았습니다.")
        }
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
        private const val NOT_UPDATED = 0
    }
}
