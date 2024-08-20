package com.doonutmate.doonut.calendar.mapper;

import com.doonutmate.doonut.calendar.entity.CalendarReportReasonEntity;
import com.doonutmate.doonut.calendar.model.CalendarReportReason;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarReportReasonMapper {
    CalendarReportReasonEntity toEntity(CalendarReportReason model);

    CalendarReportReason toModel(CalendarReportReasonEntity entity);
}
