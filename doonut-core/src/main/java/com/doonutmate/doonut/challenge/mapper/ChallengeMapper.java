package com.doonutmate.doonut.challenge.mapper;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.image.entity.ImageEntity;
import com.doonutmate.doonut.image.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Mapper
public interface ChallengeMapper {

    @Mapping(target = "days", ignore = true)
    ChallengeEntity toEntity(Challenge model);

    @Mapping(target = "days", ignore = true)
    Challenge toModel(ChallengeEntity entity);

    @Mapping(target = "imageUrl", source = "imageKey")
    @Mapping(target = "days", ignore = true)
    Challenge toChallenge(Image entity);

    @Mapping(target = "imageUrl", source = "imageKey")
    @Mapping(target = "days", ignore = true)
    List<Challenge> toChallengeList(List<Image> otherModelList);


}
