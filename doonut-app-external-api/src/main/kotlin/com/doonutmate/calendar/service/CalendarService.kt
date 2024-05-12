package com.doonutmate.calendar.service

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.calendar.controller.dto.CalendarResult
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CalendarService(
    private val calendarBusinessService: CalendarBusinessService,
    private val calendarFacadeService: CalendarFacadeService,
) {
    fun get(id: Long?, time: Instant?, page: Pageable): CalendarResult<CalendarResponse> {
        val calendarList: List<CalendarResponse> = getBoards(id, time, page)
        val lastIdOfList: Long? = if (calendarList.isEmpty()) null else calendarList[calendarList.size - 1].id
        val lastTimeOfList: Instant? = if (calendarList.isEmpty()) null else calendarList[calendarList.size - 1].updatedAt

        return CalendarResult(calendarList, hasNext(lastIdOfList, lastTimeOfList))
    }

    private fun getBoards(id: Long?, time: Instant?, page: Pageable): List<CalendarResponse> {
        return if (id != null && time != null) {
            calendarFacadeService.convertToList(calendarBusinessService.findLatestCalendar(page, time, id))
        } else {
            calendarFacadeService.convertToList(calendarBusinessService.findInitialLatestCalendar(page))
        }
    }

    private fun hasNext(id: Long?, time: Instant?): Boolean {
        return if (id == null && time == null) false else calendarBusinessService.existsNextCalendarPage(time, id)
    }
}
