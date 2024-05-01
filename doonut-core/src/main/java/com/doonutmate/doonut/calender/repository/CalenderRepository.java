package com.doonutmate.doonut.calender.repository;

import com.doonutmate.doonut.calender.entity.CalenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<CalenderEntity, Long> {
}
