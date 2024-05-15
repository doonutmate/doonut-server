package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    @Query("SELECT c FROM CalendarEntity c ORDER BY c.updatedAt DESC, c.id DESC")
    Slice<CalendarEntity> findInitialLatestCalendar(Pageable page);

    @Query("SELECT c FROM CalendarEntity c WHERE (c.updatedAt = :cursor AND c.id < :cursorId) OR c.updatedAt < :cursor ORDER BY c.updatedAt DESC, c.id DESC")
    Slice<CalendarEntity> findLatestCalendar(Instant cursor, Long cursorId, Pageable page);

    @Query("SELECT COUNT(c) > 0 FROM CalendarEntity c WHERE (c.updatedAt = :cursor AND c.id < :cursorId) OR c.updatedAt < :cursor")
    boolean existsLatestCalendar(Instant cursor, Long cursorId);

}

