package com.doonutmate.doonut.challenge.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Challenge(
        Long id,
        String memberId,
        String imageUrl,
        ChallengeType type,
        Instant createdAt,
        boolean deleted
) {
}
