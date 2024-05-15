package com.doonutmate.calendar.service

import com.doonutmate.calendar.TimeZoneConverter
import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.member.model.ImageType
import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.ProfileImage
import com.doonutmate.doonut.member.service.MemberBusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Transactional(readOnly = true)
@Service
class CalendarFacadeService(
    private val memberBusinessService: MemberBusinessService,
    private val timeZoneConverter: TimeZoneConverter,
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
            timeFormatConvert(calendar.firstUploadedAt),
            timeFormatConvert(calendar.lastUploadedAt),
            extractRepresentativeImage(member.profileImages)?.imageUrl,
        )
    }

    private fun timeFormatConvert(instant: Instant): String {
        val localDateTime = timeZoneConverter.convertInstantToLocalDateTime(instant)
        return timeZoneConverter.changeTimeFormat(localDateTime)
    }

    private fun extractRepresentativeImage(profileImages: List<ProfileImage>): ProfileImage? {
        val iterator = profileImages.listIterator()
        while (iterator.hasNext()) {
            val image = iterator.next()
            if (image.imageType == ImageType.REPRESENTATIVE) {
                return image
            }
        }
        return null
    }
}
