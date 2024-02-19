package com.doonutmate.doonut.challenge.model;

import lombok.Builder;

@Builder
public record Challenge(
        Long id,
        String memberId,
        String imageUrl,
        int days,
        boolean deleted
) {
}
