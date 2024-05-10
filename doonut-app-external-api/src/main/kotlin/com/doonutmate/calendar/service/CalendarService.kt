package com.doonutmate.calendar.service

import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CalendarService(
    private val service: CalendarBusinessService,
) {
    fun getBoards(id: Long?, time: Instant?, page: Pageable): List<Calendar> {
        return if (id == null && time == null) {
            service.findInitialLatestCalendar(page)
        } else {
            service.findLatestCalendar(page, time, id)
        }
    }
}
