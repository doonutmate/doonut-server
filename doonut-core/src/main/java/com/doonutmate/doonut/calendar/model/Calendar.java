package com.doonutmate.doonut.calendar.model;

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
        return Calendar.builder()
                .id(this.id)
                .memberId(this.memberId)
                .calendarName(this.calendarName)
                .totalCount(this.totalCount + 1)
                .firstUploadedAt(this.firstUploadedAt == null ? updatedAt : this.firstUploadedAt)
                .lastUploadedAt(updatedAt)
                .deleted(this.deleted)
                .build();
    }
}
