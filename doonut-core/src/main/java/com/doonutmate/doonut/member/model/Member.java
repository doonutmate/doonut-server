package com.doonutmate.doonut.member.model;

import lombok.Builder;

@Builder
public record Member(
        Long id,
        String name,
        String email,
        String oauthId,
        OauthType oauthType,
        boolean deleted
) {
}
