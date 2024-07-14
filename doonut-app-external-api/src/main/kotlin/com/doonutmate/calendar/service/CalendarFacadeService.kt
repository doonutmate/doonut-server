package com.doonutmate.calendar.service

import com.doonutmate.calendar.controller.dto.CalendarResponse
import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.member.model.ImageType
import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.ProfileImage
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.util.CommonDateUtils
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Transactional(readOnly = true)
@Service
class CalendarFacadeService(
    private val memberBusinessService: MemberBusinessService,
) {

    fun convertToList(calendars: Slice<Calendar>): Slice<CalendarResponse> {
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
            timeFormatConvert(calendar.firstUploadedAt),
            timeFormatConvert(calendar.lastUploadedAt),
            calendar.updatedAt,
            extractRepresentativeImage(member.profileImages)?.imageUrl,
        )
    }

    private fun timeFormatConvert(instant: Instant): String {
        val localDateTime = CommonDateUtils.convertInstantToLocalDateTime(instant)
        return CommonDateUtils.changeTimeFormat(localDateTime)
    }

    private fun extractRepresentativeImage(profileImages: List<ProfileImage>): ProfileImage? {
        return profileImages
            .filter { !it.deleted && it.imageType == ImageType.REPRESENTATIVE }
            .sortedByDescending { it.updatedAt }
            .firstOrNull()
    }
}
