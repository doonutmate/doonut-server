package com.doonutmate.doonut.member.mapper;

import com.doonutmate.doonut.member.entity.ProfileImageEntity;
import com.doonutmate.doonut.member.model.ProfileImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProfileImageMapper {

    @Mapping(target = "member", ignore = true)
    ProfileImageEntity toEntity(ProfileImage model);

    ProfileImage toModel(ProfileImageEntity entity);
}
