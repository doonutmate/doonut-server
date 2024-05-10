package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import com.doonutmate.doonut.calendar.repository.CalendarRepository;
import com.doonutmate.doonut.calendar.mapper.CalendarMapper;
import com.doonutmate.doonut.calendar.model.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CalendarBusinessService {
    private final CalendarRepository repository;
    private final CalendarMapper mapper;

    public List<Calendar> findInitialLatestCalendar(Pageable pageable) {
        List<CalendarEntity> calendarEntityList = repository.findInitialLatestCalendar(pageable);
        return convertListEntityToDto(calendarEntityList);
    }

    public List<Calendar> findLatestCalendar(Pageable pageable, Instant timeCursor, Long idCursor) {
        List<CalendarEntity> calendarEntityList = repository.findLatestCalendar(timeCursor, idCursor, pageable);
        return convertListEntityToDto(calendarEntityList);
    }

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

    public List<Calendar> convertListEntityToDto(List<CalendarEntity> calendarEntityList) {
        return calendarEntityList.stream()
                .map(calendarEntity -> get(calendarEntity.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow();
        entity.delete();
    }
}
