package com.doonutmate.calendar

import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Component
class TimeZoneConverter {

    fun convertInstantToLocalDateTime(instant: Instant): LocalDateTime {
        return instant.atZone(ZID).toLocalDateTime()
    }

    fun changeTimeFormat(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return localDateTime.format(formatter)
    }

    companion object {
        val ZID = ZoneId.of("Asia/Seoul")
    }
}
