package com.doonutmate.doonut.calendar.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Calendar(
        Long id,
        Long memberId,
        String calendarName,
        int totalCount,
        Instant firstUploadedAt,
        Instant lastUploadedAt,
        boolean deleted
) {
}
