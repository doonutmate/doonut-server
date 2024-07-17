package com.doonutmate.doonut.calendar.model;

import com.doonutmate.util.CommonDateUtils;
import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record Calendar(
        Long id,
        Long memberId,
        String calendarName,
        int totalCount,
        Instant firstUploadedAt,
        Instant lastUploadedAt,
        Instant updatedAt,
        boolean deleted
) {

    private static final String DEFAULT_CALENDAR_NAME_SUFFIX = "님 캘린더";

    public static Calendar createDefaultCalendar(Long memberId, String memberName) {
        return Calendar.builder()
                .memberId(memberId)
                .calendarName(memberName + DEFAULT_CALENDAR_NAME_SUFFIX)
                .totalCount(0)
                .firstUploadedAt(null)
                .lastUploadedAt(null)
                .deleted(false)
                .build();
    }

    public Calendar toUpdated(Instant updatedAt) {
        if (this.totalCount == 0) {
            return this.toBuilder()
                    .totalCount(this.totalCount + 1)
                    .firstUploadedAt(updatedAt)
                    .lastUploadedAt(updatedAt)
                    .build();
        }
        // 마지막 수정 날짜가 오늘이라면 totalCount를 올리지 않는다.
        if (CommonDateUtils.isToday(this.lastUploadedAt)) {
            return this.toBuilder()
                    .lastUploadedAt(updatedAt)
                    .build();
        }
        return this.toBuilder()
                .totalCount(this.totalCount + 1)
                .lastUploadedAt(updatedAt)
                .build();
    }
}
