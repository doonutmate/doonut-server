package com.doonutmate.doonut.challenge.mapper;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.image.entity.ImageEntity;
import com.doonutmate.doonut.image.model.Image;
import org.mapstruct.Mapper;

@Mapper
public interface ChallengeMapper {
    ChallengeEntity toEntity(Challenge model);

    Challenge toModel(ChallengeEntity entity);
}
