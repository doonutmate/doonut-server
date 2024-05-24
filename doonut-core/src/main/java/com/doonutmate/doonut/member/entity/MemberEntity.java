package com.doonutmate.doonut.member.entity;

import com.doonutmate.doonut.common.entity.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    String name;

    @Column
    String email;

    @Setter
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<ProfileImageEntity> profileImages = new ArrayList<>();

    @Embedded
    OauthInfo oauthInfo;

    @Builder.Default
    @Column
    boolean deleted = false;

    public void delete() {
        deleted = true;
    }

    public void updateNameOrProfileImage(String name, List<ProfileImageEntity> newProfileImages) {
        this.name = name;
        this.profileImages = newProfileImages;
    }
}
