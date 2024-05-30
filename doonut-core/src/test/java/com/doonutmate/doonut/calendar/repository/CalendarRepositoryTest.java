package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository repository;

    @Test
    @DisplayName("첫 캘린더를 조회할 때 totalCount가 3 이상인 캘린더만을 가져온다.")
    void findInitialLatestCalendar1() {

        // given
        var memberId = 10L;
        var now = Instant.now();
        var entity1 = CalendarEntity.builder()
                .memberId(1L)
                .calendarName("캘린더명1")
                .totalCount(2)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        var entity2 = CalendarEntity.builder()
                .memberId(2L)
                .calendarName("캘린더명2")
                .totalCount(3)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        var entity3 = CalendarEntity.builder()
                .memberId(3L)
                .calendarName("캘린더명3")
                .totalCount(4)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        repository.save(entity1);
        repository.save(entity2);
        repository.save(entity3);

        // when
        var actual = repository.findInitialLatestCalendar(Pageable.ofSize(10), memberId);

        // then
        assertThat(actual.getContent())
                .hasSize(2)
                .extracting("calendarName")
                .contains(entity2.getCalendarName(), entity3.getCalendarName());
    }

    @Test
    @DisplayName("첫 캘린더를 조회할 때 캘린더명이 설정된 캘린더만을 가져온다.")
    void findInitialLatestCalendar2() {

        // given
        var memberId = 10L;
        var now = Instant.now();
        var entity1 = CalendarEntity.builder()
                .memberId(2L)
                .calendarName("")
                .totalCount(3)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        var entity2 = CalendarEntity.builder()
                .memberId(3L)
                .calendarName("캘린더명3")
                .totalCount(4)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        repository.saveAll(List.of(entity1, entity2));

        // when
        var actual = repository.findInitialLatestCalendar(Pageable.ofSize(10), memberId);

        // then
        assertThat(actual.getContent())
                .hasSize(1)
                .extracting("calendarName")
                .containsExactly(entity2.getCalendarName());
    }

    @Test
    @DisplayName("첫 캘린더를 조회할 때 자신의 캘린더는 제외한다.")
    void findInitialLatestCalendar3() {

        // given
        var now = Instant.now();
        var entity = CalendarEntity.builder()
                .memberId(1L)
                .calendarName("캘린더명")
                .totalCount(3)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        repository.save(entity);

        // when
        var actual = repository.findInitialLatestCalendar(Pageable.ofSize(10), entity.getMemberId());

        // then
        assertThat(actual.getContent()).isEmpty();
    }

    @Test
    @DisplayName("최신 캘린더를 조회할 때 totalCount가 3 이상인 캘린더만을 가져온다.")
    void findLatestCalendar1() {

        // given
        var memberId = 10L;
        var now = Instant.now();
        var entity1 = CalendarEntity.builder()
                .memberId(1L)
                .calendarName("캘린더명1")
                .totalCount(2)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        var entity2 = CalendarEntity.builder()
                .memberId(2L)
                .calendarName("캘린더명2")
                .totalCount(3)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        var entity3 = CalendarEntity.builder()
                .memberId(3L)
                .calendarName("캘린더명3")
                .totalCount(4)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        repository.saveAll(List.of(entity1, entity2, entity3));

        // when
        var actual = repository.findLatestCalendar(now.plusSeconds(100), Pageable.ofSize(10), memberId);

        // then
        assertThat(actual.getContent())
                .hasSize(2)
                .extracting("calendarName")
                .contains(entity2.getCalendarName(), entity3.getCalendarName());
    }

    @Test
    @DisplayName("최신 캘린더를 조회할 때 캘린더명이 설정된 캘린더만을 가져온다.")
    void findLatestCalendar2() {

        // given
        var memberId = 10L;
        var now = Instant.now();
        var entity1 = CalendarEntity.builder()
                .memberId(2L)
                .calendarName("")
                .totalCount(3)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        var entity2 = CalendarEntity.builder()
                .memberId(3L)
                .calendarName("캘린더명3")
                .totalCount(4)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        repository.saveAll(List.of(entity1, entity2));

        // when
        var actual = repository.findLatestCalendar(now.plusSeconds(100), Pageable.ofSize(10), memberId);

        // then
        assertThat(actual.getContent())
                .hasSize(1)
                .extracting("calendarName")
                .containsExactly(entity2.getCalendarName());
    }

    @Test
    @DisplayName("최신 캘린더를 조회할 때 자신의 캘린더는 제외한다.")
    void findLatestCalendar3() {

        // given
        var now = Instant.now();
        var entity = CalendarEntity.builder()
                .memberId(1L)
                .calendarName("캘린더명")
                .totalCount(3)
                .firstUploadedAt(now.plusSeconds(10))
                .lastUploadedAt(now.plusSeconds(20))
                .deleted(false)
                .build();
        repository.save(entity);

        // when
        var actual = repository.findLatestCalendar(now, Pageable.ofSize(10), entity.getMemberId());

        // then
        assertThat(actual.getContent()).isEmpty();
    }
}
