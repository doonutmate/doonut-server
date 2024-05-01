package com.doonutmate.doonut.calender.mapper;

import com.doonutmate.doonut.calender.entity.CalenderEntity;
import com.doonutmate.doonut.calender.model.Calender;
import org.mapstruct.Mapper;

@Mapper
public interface CalenderMapper {

    CalenderEntity toEntity(Calender model);

    Calender toModel(CalenderEntity entity);
}
