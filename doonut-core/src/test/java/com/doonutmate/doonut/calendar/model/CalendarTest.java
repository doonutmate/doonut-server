package com.doonutmate.doonut.calendar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarTest {

    @Test
    @DisplayName("firstUploadedAt가 null이라면 totalCount, lastUploadedAt만 업데이트한다.")
    void toUpdated_ifFirstUploadedAtIsNull() {

        // given
        var now = Instant.now();
        var calendar = Calendar.builder()
                .id(1L)
                .memberId(10L)
                .calendarName("부끄러운 물개님의 캘린더")
                .firstUploadedAt(null)
                .lastUploadedAt(now.minusSeconds(500))
                .deleted(false)
                .build();
        var expectedTotalCount = calendar.totalCount() + 1;
        var expected = Calendar.builder()
                .id(calendar.id())
                .memberId(calendar.memberId())
                .calendarName(calendar.calendarName())
                .totalCount(expectedTotalCount)
                .firstUploadedAt(now)
                .lastUploadedAt(now)
                .deleted(false)
                .build();

        // when
        var actual = calendar.toUpdated(now);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("firstUploadedAt가 null이 아니라면 totalCount, lastUploadedAt만 업데이트한다.")
    void toUpdated_ifFirstUploadedAtIsNotNull() {

        // given
        var now = Instant.now();
        var calendar = Calendar.builder()
                .id(1L)
                .memberId(10L)
                .calendarName("부끄러운 물개님의 캘린더")
                .firstUploadedAt(now.minusSeconds(100))
                .lastUploadedAt(now.minusSeconds(500))
                .deleted(false)
                .build();
        var expectedTotalCount = calendar.totalCount() + 1;
        var expected = Calendar.builder()
                .id(calendar.id())
                .memberId(calendar.memberId())
                .calendarName(calendar.calendarName())
                .totalCount(expectedTotalCount)
                .firstUploadedAt(calendar.firstUploadedAt())
                .lastUploadedAt(now)
                .deleted(false)
                .build();

        // when
        var actual = calendar.toUpdated(now);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
