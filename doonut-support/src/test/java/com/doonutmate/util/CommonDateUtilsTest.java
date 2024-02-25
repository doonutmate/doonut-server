package com.doonutmate.util;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

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
}
