package com.doonutmate.doonut.image.model;

import lombok.Builder;

@Builder
public record Image(
        Long id,
        Long memberId,
        String oriImageName,
        String imageHostUrl,
        int height,
        int width,
        long capacity,
        boolean deleted

) {
}
