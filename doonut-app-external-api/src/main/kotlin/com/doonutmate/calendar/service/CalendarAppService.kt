package com.doonutmate.calendar.service

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CalendarAppService(
    private val calendarBusinessService: CalendarBusinessService,
    private val calendarFacadeService: CalendarFacadeService,
) {
    fun get(id: Long?, time: Instant?, page: Pageable): CalendarResult<CalendarResponse> {
        val calendars: Slice<CalendarResponse> = getBoards(id, time, page)

        return CalendarResult(calendars, calendars.hasNext())
    }

    private fun getBoards(id: Long?, time: Instant?, page: Pageable): Slice<CalendarResponse> {
        return if (id != null && time != null) {
            calendarFacadeService.convertToList(calendarBusinessService.findLatestCalendar(page, time, id))
        } else {
            calendarFacadeService.convertToList(calendarBusinessService.findInitialLatestCalendar(page))
        }
    }
}
