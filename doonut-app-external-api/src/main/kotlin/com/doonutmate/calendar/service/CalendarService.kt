package com.doonutmate.calendar.service

import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CalendarService(
    private val service: CalendarBusinessService,
) {

    fun getNextPage(cursor: Long?): List<Calendar> {
        return fetchCalendars(cursor ?: service.randomCursor)
    }

    private fun fetchCalendars(cursor: Long?): List<Calendar> {
        val calendars = service.findByCursor(cursor, PageRequest.of(0, 10))
        if (calendars.isEmpty()) {
            return service.findByCursor(service.initialCursor, PageRequest.of(0, 10))
        }
        return calendars
    }
}
