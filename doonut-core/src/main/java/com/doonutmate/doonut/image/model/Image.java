package com.doonutmate.doonut.image.model;

import lombok.Builder;

@Builder
public record Image(
        Long id,
        Long memberId,
        String imageKey,
        String oriImageName,
        String imageHostUrl,
        int height,
        int width,
        int capacity,
        boolean deleted
) {
}
