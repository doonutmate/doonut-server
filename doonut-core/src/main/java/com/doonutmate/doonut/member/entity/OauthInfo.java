package com.doonutmate.doonut.member.entity;

import com.doonutmate.doonut.member.model.OauthType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Embeddable
public class OauthInfo {

    @Column
    @EqualsAndHashCode.Include
    String oauthId;

    @Enumerated(EnumType.STRING)
    @Column
    @EqualsAndHashCode.Include
    OauthType oauthType;
}
