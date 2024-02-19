package com.doonutmate.doonut.challenge.model;

import lombok.Builder;

@Builder
public record Challenge(
        Long id,
        Long memberId,
        String imageUrl,
        boolean deleted
) {
}
