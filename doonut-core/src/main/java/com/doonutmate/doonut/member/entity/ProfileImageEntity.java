package com.doonutmate.doonut.member.entity;

import com.doonutmate.doonut.common.entity.BaseTimeEntity;
import com.doonutmate.doonut.member.model.ImageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "profile_image")
public class ProfileImageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ImageType imageType;

    @Column(nullable = false, length = 2000)
    private String imageUrl;

    @Builder.Default
    @Column
    boolean deleted = false;

    public void delete() {
        deleted = true;
    }
}
