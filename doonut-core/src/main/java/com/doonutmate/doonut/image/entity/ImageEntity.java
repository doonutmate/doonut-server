package com.doonutmate.doonut.image.entity;

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
@Table(name = "image")
public class ImageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    Long memberId;

    @Column
    String imageKey;

    @Column
    String oriImageName;

    @Column
    String imageHostUrl;

    @Column
    int height;

    @Column
    int width;

    @Column
    int capacity;

    @Column
    boolean deleted;
}
