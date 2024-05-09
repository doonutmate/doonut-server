package com.doonutmate.doonut.calendar.service;

import com.doonutmate.doonut.calendar.entity.CalendarEntity;
import com.doonutmate.doonut.calendar.repository.CalendarRepository;
import com.doonutmate.doonut.calendar.mapper.CalendarMapper;
import com.doonutmate.doonut.calendar.model.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CalendarBusinessService {
    private final CalendarRepository repository;
    private final CalendarMapper mapper;


    private final static int FIRST_INDEX = 0;

    public List<Calendar> findByCursor(Long cursor, Pageable pageable) {
        List<CalendarEntity> calendarEntityList = repository.findByCursor(cursor, pageable);

        return calendarEntityList.stream()
                .map(calendarEntity -> get(calendarEntity.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Long getInitialCursor() {
        List<CalendarEntity> calendars = findFirstCalendar(FIRST_INDEX);
        return extractFirstItemId(calendars);
    }

    public Long getRandomCursor() {
        long count = repository.countByDeletedFalse();
        int randomIndex = new Random().nextInt((int) count);
        List<CalendarEntity> calendars =findFirstCalendar(randomIndex);
        return extractFirstItemId(calendars);
    }

    private List<CalendarEntity> findFirstCalendar(int index) {
        return repository.findFirstByDeletedFalseOrderByIdAsc(PageRequest.of(index, 1));
    }


    private Long extractFirstItemId(List<CalendarEntity> calendars) {
        return calendars.isEmpty() ? null : calendars.get(0).getId();
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

    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow();
        entity.delete();
    }
}
