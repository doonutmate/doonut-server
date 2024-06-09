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
        boolean deleted
) {

    public Calendar toUpdated(Instant updatedAt) {
        return Calendar.builder()
                .id(this.id)
                .memberId(this.memberId)
                .calendarName(this.calendarName)
                .totalCount(this.totalCount + 1)
                .firstUploadedAt(this.firstUploadedAt == null ? updatedAt : this.firstUploadedAt)
                .lastUploadedAt(updatedAt)
                .build();
    }
}
