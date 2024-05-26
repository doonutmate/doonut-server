package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.mapper.CalendarReportReasonMapper;
import com.doonutmate.doonut.calendar.model.CalendarReportReason;
import com.doonutmate.doonut.calendar.repository.CalendarReportReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CalendarReportReasonBusinessService {

    private final CalendarReportReasonRepository repository;
    private final CalendarReportReasonMapper mapper;

    @Transactional
    public Long create(CalendarReportReason calendarReportReason) {
        var newEntity = mapper.toEntity(calendarReportReason);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public CalendarReportReason get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }

}
