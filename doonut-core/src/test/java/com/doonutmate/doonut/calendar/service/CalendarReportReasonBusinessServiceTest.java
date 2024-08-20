package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.model.Calendar;
import com.doonutmate.doonut.calendar.model.CalendarReportReason;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class CalendarReportReasonBusinessServiceTest {

    @Autowired
    private CalendarReportReasonBusinessService service;

    @DisplayName("캘린더 신고 사유 생성 테스트")
    @Test
    void createCalendarReportReason() {

        //given
        var reporterId = 1L;
        var calendarId = 10L;
        var reason = "테스트입니다";
        var expected = generateCalendarReason(reporterId, calendarId, reason);
        var CalendarReasonId = service.create(expected);

        //when
        var actual = service.get(CalendarReasonId);

        //then
        assertThat(actual)
                .extracting("id", "reporterId", "calendarId", "reason")
                .containsExactly(CalendarReasonId, reporterId, calendarId, reason);
    }


    private CalendarReportReason generateCalendarReason(Long reporterId, Long calendarId, String reason) {
        return CalendarReportReason.builder()
                .reporterId(reporterId)
                .calendarId(calendarId)
                .reason(reason)
                .build();
    }

}