package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    @Query("SELECT c FROM CalendarEntity c WHERE c.deleted = false")
    List<CalendarEntity> findFirstByDeletedFalseOrderByIdAsc(Pageable pageable);

    @Query("SELECT count(c) FROM CalendarEntity c WHERE c.deleted = false")
    long countByDeletedFalse();

    @Query("SELECT c FROM CalendarEntity c WHERE c.deleted = false AND c.id > :cursor ORDER BY c.id ASC")
    List<CalendarEntity> findByCursor(Long cursor, Pageable pageable);
}
