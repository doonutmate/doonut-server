package com.doonutmate.doonut.challenge.entity;

import com.doonutmate.doonut.challenge.model.ChallengeType;
import com.doonutmate.doonut.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "challenge")
public class ChallengeEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    Long memberId;

    @Column
    String imageUrl;

    @Column
    ChallengeType type;

    @Column
    boolean deleted;
}
