package com.doonutmate.doonut.calender.model;

import java.time.Instant;

public record Calender(
        Long id,
        Long memberId,
        String calenderName,
        int totalCount,
        Instant firstUploadDate,
        Instant lastUploadDate,
        boolean deleted
) {
}
