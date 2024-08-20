package com.doonutmate.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CommonDateUtilsTest {

    @Test
    void getInstant() {
        // given
        int year = 2024;
        int month = 2;
        int day = 1;

        // when
        Instant result = CommonDateUtils.getInstant(year, month, day);

        // then
        assertThat(result).isEqualTo(Instant.parse("2024-01-31T15:00:00.000000Z"));
    }

    @Test
    void getFirst() {
        // given
        int year = 2024;
        int month = 2;

        // when
        Instant result = CommonDateUtils.getFirst(year, month);

        // then
        assertThat(result).isEqualTo(Instant.parse("2024-01-31T15:00:00.000000Z"));
    }

    @Test
    void getLast() {
        // given
        int year = 2024;
        int month = 2;

        // when
        Instant result = CommonDateUtils.getLast(year, month);

        // then
        assertThat(result).isEqualTo(Instant.parse("2024-02-29T14:59:59.000Z"));
    }

    @ParameterizedTest
    @MethodSource("getYearMonthDay_provider")
    void getYearMonthDay(Instant instant, String expected) {

        // when
        var actual = CommonDateUtils.getYearMonthDay(instant);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getYearMonthDay_provider() {
        return Stream.of(
                arguments(CommonDateUtils.getInstant(2023, 12, 30), "20231230"),
                arguments(CommonDateUtils.getInstant(2023, 12, 31), "20231231"),
                arguments(CommonDateUtils.getInstant(2024, 1, 1), "20240101"),
                arguments(CommonDateUtils.getInstant(2024, 1, 31), "20240131"),
                arguments(CommonDateUtils.getInstant(2024, 2, 1), "20240201"),
                arguments(CommonDateUtils.getInstant(2024, 3, 1), "20240301")
        );
    }

    @ParameterizedTest
    @MethodSource("getDay_provider")
    void getDay(Instant instant, int expected) {

        // when
        var actual = CommonDateUtils.getDay(instant);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getDay_provider() {
        return Stream.of(
                arguments(CommonDateUtils.getInstant(2024, 1, 30), 30),
                arguments(CommonDateUtils.getInstant(2024, 1, 31), 31),
                arguments(CommonDateUtils.getInstant(2024, 2, 1), 1),
                arguments(CommonDateUtils.getInstant(2024, 2, 29), 29),
                arguments(CommonDateUtils.getInstant(2024, 3, 1), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("isToday_provider")
    void isToday(Instant instant, boolean expected) {

        // when
        var actual = CommonDateUtils.isToday(instant);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    private static Stream<Arguments> isToday_provider() {
        var now = Instant.now();
        return Stream.of(
                arguments(now, true),
                arguments(null, false),
                arguments(now.minusSeconds(86400), false),
                arguments(now.plusSeconds(86400), false)
        );
    }
}
