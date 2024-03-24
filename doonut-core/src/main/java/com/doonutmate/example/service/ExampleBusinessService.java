package com.doonutmate.example.service;

import com.doonutmate.example.mapper.ExampleMapper;
import com.doonutmate.example.model.Example;
import com.doonutmate.example.repository.ExampleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExampleBusinessService {

    private final ExampleRepository repository;

    private final ExampleMapper mapper;

    @Transactional
    public Long create(Example example) {
        var newEntity = mapper.toEntity(example);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Example get(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.toModel(entity);
    }
}
