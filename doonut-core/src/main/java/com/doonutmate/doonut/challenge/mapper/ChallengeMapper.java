package com.doonutmate.doonut.challenge.mapper;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import com.doonutmate.doonut.challenge.model.Challenge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ChallengeMapper {

    ChallengeEntity toEntity(Challenge model);

    Challenge toModel(ChallengeEntity entity);
}
