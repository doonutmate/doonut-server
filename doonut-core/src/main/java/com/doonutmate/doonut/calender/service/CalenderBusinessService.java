package com.doonutmate.doonut.calender.service;

import com.doonutmate.doonut.calender.mapper.CalenderMapper;
import com.doonutmate.doonut.calender.model.Calender;
import com.doonutmate.doonut.calender.repository.CalenderRepository;
import com.doonutmate.doonut.challenge.model.Challenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CalenderBusinessService {
    private final CalenderRepository repository;
    private final CalenderMapper mapper;

    @Transactional
    public Long create(Calender calender) {
        var newEntity = mapper.toEntity(calender);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Calender get(Long id) {
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
