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
        assertThat(result).isEqualTo(Instant.parse("2024-02-29T14:59:59.999Z"));
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
}
