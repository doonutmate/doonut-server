package com.doonutmate.doonut.member.model;

import lombok.Builder;

@Builder
public record MemberDeleteReason(
        Long id,
        Long memberId,
        String reason
) {
}
