package com.doonutmate.doonut.member.model;

import lombok.Builder;

@Builder
public record ProfileImage(
        Long id,
        ImageType imageType,
        String imageUrl
) {
}
