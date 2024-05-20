package com.doonutmate.doonut.calendar.model;

import lombok.Builder;

@Builder
public record CalendarReportReason(
        Long id,
        Long reporterId,
        Long calendarId,
        String reason
) {
    public CalendarReportReason of(Long reporterId, Long calendarId, String reason) {
        return new CalendarReportReason(null, reporterId, calendarId, reason);
    }
}
