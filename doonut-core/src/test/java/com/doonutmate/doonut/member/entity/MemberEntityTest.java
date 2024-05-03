package com.doonutmate.doonut.member.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberEntityTest {

    @DisplayName("엔티티내 delete 함수 호출시 deleted값 true로 변경")
    @Test
    void changeDeleteState() {
        // given
        MemberEntity entity = MemberEntity.builder()
                .deleted(false)
                .build();

        // when
        entity.delete();

        // then
        assertThat(entity.isDeleted()).isTrue();
    }
}
