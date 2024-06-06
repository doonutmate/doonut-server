package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.model.Calendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CalendarBusinessServiceTest {

    @Autowired
    private CalendarBusinessService service;

    @Test
    void getId() {

        // given
        var expected = generateCalendar(2L);
        var calendarId = service.create(expected);

        // when
        var actual = service.get(calendarId);

        // then
        assertThat(actual)
                .extracting("id", "memberId", "calendarName", "totalCount")
                .containsExactly(calendarId, 2L, expected.calendarName(), expected.totalCount());
    }

    @Test
    void getByMemberId() {

        // given
        var memberId = 1L;
        var expected = generateCalendar(memberId);
        var calendarId = service.create(expected);

        // when
        var actual = service.getByMemberId(memberId);

        // then
        assertThat(actual)
                .extracting("id", "memberId", "calendarName", "totalCount")
                .containsExactly(calendarId, memberId, expected.calendarName(), expected.totalCount());
    }

    private Calendar generateCalendar(long memberId) {
        return Calendar.builder()
                .memberId(memberId)
                .calendarName("캘린더명")
                .totalCount(10)
                .firstUploadedAt(Instant.now())
                .lastUploadedAt(Instant.now())
                .deleted(false)
                .build();
    }
}
