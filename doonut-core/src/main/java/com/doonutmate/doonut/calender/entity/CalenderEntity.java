package com.doonutmate.doonut.calender.entity;


import com.doonutmate.doonut.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "calender")
public class CalenderEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column
    Long memberId;

    @Column
    String calenderName;

    @Builder.Default
    @Column
    int totalCount = 0;

    @Column
    Instant firstUploadDate;

    @Column
    Instant lastUploadDate;

    @Builder.Default
    @Column
    boolean deleted = false;

    public void delete() {
        deleted = true;
    }
}
