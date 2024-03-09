package com.doonutmate.doonut.member.event;

import lombok.Builder;

@Builder
public record MemberDeleteEvent(
        Long id
) {
}
