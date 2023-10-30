package com.doonutmate.doonut.member.mapper;

import com.doonutmate.doonut.member.entity.MemberEntity;
import com.doonutmate.doonut.member.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MemberMapper {

    @Mapping(target = "profileImages", ignore = true)
    @Mapping(target = "oauthInfo.oauthId", source = "oauthId")
    @Mapping(target = "oauthInfo.oauthType", source = "oauthType")
    MemberEntity toEntity(Member model);

    @Mapping(target = "oauthId", source = "oauthInfo.oauthId")
    @Mapping(target = "oauthType", source = "oauthInfo.oauthType")
    Member toModel(MemberEntity entity);
}
