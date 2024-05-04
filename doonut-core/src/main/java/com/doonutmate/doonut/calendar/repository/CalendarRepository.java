package com.doonutmate.doonut.calendar.repository;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
}
