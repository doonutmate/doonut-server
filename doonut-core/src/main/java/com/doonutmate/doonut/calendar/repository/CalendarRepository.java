package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    @Query("SELECT c FROM CalendarEntity c ORDER BY c.createdAt DESC, c.id DESC")
    List<CalendarEntity> findInitialLatestCalendar(Pageable page);

    @Query("SELECT c FROM CalendarEntity c WHERE (c.createdAt = :cursor AND c.id < :cursorId) OR c.createdAt < :cursor ORDER BY c.createdAt DESC, c.id DESC")
    List<CalendarEntity> findLatestCalendar(Instant cursor, Long cursorId, Pageable page);
}

