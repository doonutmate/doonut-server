package com.doonutmate.example.mapper;

import com.doonutmate.example.entity.ExampleEntity;
import com.doonutmate.example.model.Example;
import org.mapstruct.Mapper;

@Mapper
public interface ExampleMapper {

    ExampleEntity toEntity(Example model);

    Example toModel(ExampleEntity entity);
}
