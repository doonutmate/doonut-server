package com.doonutmate.doonut.calendar.mapper;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import com.doonutmate.doonut.calendar.model.Calendar;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarMapper {

    CalendarEntity toEntity(Calendar model);

    Calendar toModel(CalendarEntity entity);
}
