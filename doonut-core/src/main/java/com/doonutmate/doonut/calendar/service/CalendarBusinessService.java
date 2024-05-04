package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.repository.CalendarRepository;
import com.doonutmate.doonut.calendar.mapper.CalendarMapper;
import com.doonutmate.doonut.calendar.model.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CalendarBusinessService {
    private final CalendarRepository repository;
    private final CalendarMapper mapper;

    @Transactional
    public Long create(Calendar calendar) {
        var newEntity = mapper.toEntity(calendar);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Calendar get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow();
        entity.delete();
    }
}
