package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import com.doonutmate.doonut.calendar.repository.CalendarRepository;
import com.doonutmate.doonut.calendar.mapper.CalendarMapper;
import com.doonutmate.doonut.calendar.model.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
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

    public Slice<Calendar> findInitialLatestCalendar(Pageable pageable) {
        Slice<CalendarEntity> calendarEntityList = repository.findInitialLatestCalendar(pageable);
        return convertListEntityToDto(calendarEntityList);
    }

    public Slice<Calendar> findLatestCalendar(Pageable pageable, Instant timeCursor) {
        Slice<CalendarEntity> calendarEntityList = repository.findLatestCalendar(timeCursor, pageable);
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

    public Slice<Calendar> convertListEntityToDto(Slice<CalendarEntity> calendarEntityList) {
        List<Calendar> content = calendarEntityList.getContent().stream()
                .map(calendarEntity -> get(calendarEntity.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new SliceImpl<>(content, calendarEntityList.getPageable(), calendarEntityList.hasNext());
    }

    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow();
        entity.delete();
    }
}
