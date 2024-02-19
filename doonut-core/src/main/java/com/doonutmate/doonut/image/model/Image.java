package com.doonutmate.doonut.image.model;

import lombok.Builder;

@Builder
public record Image(
        Long id,
        String memberId,
        String imageKey,
        String oriImageName,
        String imageHostUrl,
        int height,
        int width,
        int capacity,
        boolean deleted
) {
}
