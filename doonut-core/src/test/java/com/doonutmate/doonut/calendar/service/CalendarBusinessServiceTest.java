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
    void updateCalendarName() {

        // given
        var memberId = 1L;
        service.create(generateCalendar(memberId));
        var newCalendarName = "새로운 캘린더명";

        // when
        service.updateCalendarName(memberId, newCalendarName);

        // then
        var actual = service.getByMemberId(memberId);
        assertThat(actual.calendarName()).isEqualTo(newCalendarName);
    }

    @Test
    void update() {

        // given
        var memberId = 1L;
        service.create(generateCalendar(memberId));
        var calendar = service.getByMemberId(memberId);
        var expected = calendar.toBuilder()
                .totalCount(calendar.totalCount() + 1)
                .firstUploadedAt(Instant.now().minusSeconds(500))
                .lastUploadedAt(Instant.now().minusSeconds(100))
                .build();

        // when
        service.update(expected);

        // then
        var actual = service.getByMemberId(memberId);
        assertThat(actual)
                .extracting("memberId", "calendarName", "totalCount", "firstUploadedAt", "lastUploadedAt")
                .containsExactly(memberId, expected.calendarName(), expected.totalCount(), expected.firstUploadedAt(), expected.lastUploadedAt());
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
