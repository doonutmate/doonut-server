package com.doonutmate.example.service;

import com.doonutmate.example.entity.ExampleEntity;
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

    @Transactional
    public Long create(Example example) {
        var id = example.id();
        var name = example.name();

        var savedEntity = repository.save(ExampleEntity.builder()
                .id(id)
                .name(name)
                .build());

        return savedEntity.getId();
    }

    public Example get(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return new Example(entity.getId(), entity.getName());
    }
}
