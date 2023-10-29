package com.doonutmate.doonut.member.mapper;

import com.doonutmate.doonut.member.entity.MemberEntity;
import com.doonutmate.doonut.member.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MemberMapper {

    @Mapping(target = "profileImages", ignore = true)
    MemberEntity toEntity(Member model);

    Member toModel(MemberEntity entity);
}
