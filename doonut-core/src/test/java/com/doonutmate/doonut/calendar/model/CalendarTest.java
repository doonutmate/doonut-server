package com.doonutmate.doonut.calendar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarTest {

    @Test
    @DisplayName("totalCount가 0이라면 totalCount, firstUploadedAt, lastUploadedAt을 업데이트한다.")
    void toUpdated_ifTotalCountIsZero() {

        // given
        var now = Instant.now();
        var calendar = Calendar.builder()
                .id(1L)
                .memberId(10L)
                .calendarName("부끄러운 물개님의 캘린더")
                .firstUploadedAt(null)
                .lastUploadedAt(null)
                .totalCount(0)
                .deleted(false)
                .build();
        var expected = Calendar.builder()
                .id(calendar.id())
                .memberId(calendar.memberId())
                .calendarName(calendar.calendarName())
                .totalCount(calendar.totalCount() + 1)
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
    @DisplayName("lastUploadedAt가 오늘이라면 lastUploadedAt만 업데이트한다.")
    void toUpdated_ifLastUploadedAtIsToday() {

        // given
        var now = Instant.now();
        var calendar = Calendar.builder()
                .id(1L)
                .memberId(10L)
                .calendarName("부끄러운 물개님의 캘린더")
                .firstUploadedAt(now.minusSeconds(3))
                .lastUploadedAt(now.minusSeconds(3))
                .totalCount(1)
                .deleted(false)
                .build();
        var expected = Calendar.builder()
                .id(calendar.id())
                .memberId(calendar.memberId())
                .calendarName(calendar.calendarName())
                .totalCount(calendar.totalCount())
                .firstUploadedAt(calendar.firstUploadedAt())
                .lastUploadedAt(now)
                .deleted(false)
                .build();

        // when
        var actual = calendar.toUpdated(now);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("totalCount가 0이 아니고 lastUploadedAt가 오늘이 아니라면, totalCount, lastUploadedAt을 업데이트한다.")
    void toUpdated_ifLastUploadedAtIsNotToday() {

        // given
        var now = Instant.now();
        var tomorrow = now.plusSeconds(86400);
        var calendar = Calendar.builder()
                .id(1L)
                .memberId(10L)
                .calendarName("부끄러운 물개님의 캘린더")
                .firstUploadedAt(now)
                .lastUploadedAt(now.minusSeconds(3))
                .totalCount(1)
                .deleted(false)
                .build();
        var expected = Calendar.builder()
                .id(calendar.id())
                .memberId(calendar.memberId())
                .calendarName(calendar.calendarName())
                .totalCount(calendar.totalCount() + 1)
                .firstUploadedAt(calendar.firstUploadedAt())
                .lastUploadedAt(tomorrow)
                .deleted(false)
                .build();

        // when
        var actual = calendar.toUpdated(tomorrow);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
