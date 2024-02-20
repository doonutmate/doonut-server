package com.doonutmate.doonut.image.mapper;

import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.image.entity.ImageEntity;
import com.doonutmate.doonut.image.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ImageMapper {

    ImageEntity toEntity(Image model);

    Image toModel(ImageEntity entity);

}
