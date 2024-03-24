package com.doonutmate.doonut.member.mapper;

import com.doonutmate.doonut.member.entity.MemberDeleteReasonEntity;
import com.doonutmate.doonut.member.model.MemberDeleteReason;
import org.mapstruct.Mapper;

@Mapper
public interface MemberDeleteReasonMapper {

    MemberDeleteReasonEntity toEntity(MemberDeleteReason model);

    MemberDeleteReason toModel(MemberDeleteReasonEntity entity);
}
