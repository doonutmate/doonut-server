package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    @Query("""
            SELECT c
            FROM CalendarEntity c
            WHERE LENGTH(c.calendarName) >= 1
            AND c.totalCount >= 3
            AND c.memberId != :memberId
            ORDER BY c.updatedAt DESC
             """)
    Slice<CalendarEntity> findInitialLatestCalendar(Pageable page, Long memberId);

    @Query("""
            SELECT c FROM CalendarEntity c
            WHERE c.updatedAt < :cursor
            AND LENGTH(c.calendarName) >= 1
            AND c.totalCount >= 3
            AND c.memberId != :memberId
            ORDER BY c.updatedAt DESC
             """)
    Slice<CalendarEntity> findLatestCalendar(Instant cursor, Pageable page, Long memberId);

    @Modifying
    @Query("UPDATE CalendarEntity c SET c.calendarName = :newName WHERE c.memberId = :memberId")
    int updateCalendarNameByMemberId(Long memberId, String newName);

    Optional<CalendarEntity> findByMemberId(Long memberId);
}
