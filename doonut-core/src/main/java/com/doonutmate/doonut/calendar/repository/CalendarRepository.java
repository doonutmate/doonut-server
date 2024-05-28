package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CalendarEntity c SET c.calendarName = :newName WHERE c.memberId = :memberId")
    int updateCalendarNameByMemberId(Long memberId, String newName);

    @Query("SELECT c FROM CalendarEntity c ORDER BY c.updatedAt DESC")
    Slice<CalendarEntity> findInitialLatestCalendar(Pageable page);

    @Query("SELECT c FROM CalendarEntity c WHERE c.updatedAt < :cursor ORDER BY c.updatedAt DESC")
    Slice<CalendarEntity> findLatestCalendar(Instant cursor, Pageable page);

    Optional<CalendarEntity> findByMemberId(Long memberId);
}
