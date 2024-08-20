package com.doonutmate.doonut.member.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ProfileImage(
        Long id,
        ImageType imageType,
        String imageUrl,
        Instant updatedAt,
        boolean deleted
) {
}
