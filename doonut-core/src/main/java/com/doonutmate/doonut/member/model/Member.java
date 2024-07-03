package com.doonutmate.doonut.member.model;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record Member(
        Long id,
        String name,
        String email,
        List<ProfileImage> profileImages,
        String oauthId,
        OauthType oauthType,
        boolean serviceAlarm,
        boolean lateNightAlarm,
        boolean marketingReceiveConsent,
        Instant marketingReceiveConsentUpdatedAt,
        String fcmToken,
        boolean deleted
) {
}
