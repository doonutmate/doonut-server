package com.doonutmate.doonut.member.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Member(
        Long id,
        String name,
        String email,
        List<ProfileImage> profileImages,
        String oauthId,
        OauthType oauthType,
        boolean deleted
) {
}
