package com.doonutmate.calendar.service

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.service.MemberBusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CalendarFacadeService(
    private val memberBusinessService: MemberBusinessService,
) {

    fun convertToList(calendars: List<Calendar>): List<CalendarResponse> {
        return calendars.map { calendar ->
            convert(calendar)
        }
    }

    private fun convert(calendar: Calendar): CalendarResponse {
        val member: Member = memberBusinessService.get(calendar.memberId)
        return CalendarResponse(
            calendar.id,
            calendar.totalCount,
            member.name,
            calendar.calendarName,
            calendar.updatedAt,
            calendar.firstUploadedAt,
            calendar.lastUploadedAt,
            member.profileImages,
        )
    }
}
