package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.model.MemberDeleteReason;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberDeleteReasonBusinessServiceTest {

    @Autowired
    private MemberDeleteReasonBusinessService service;

    @Test
    void create() {
        // given
        var model = MemberDeleteReason.builder()
                .memberId(1L)
                .reason("보안이 걱정돼요")
                .build();

        // when
        var savedEntityId = service.create(model);

        // then
        var saved = service.get(savedEntityId);
        assertThat(saved.reason()).isEqualTo(model.reason());
    }

    @Test
    void get() {
        // given
        var model = MemberDeleteReason.builder()
                .memberId(1L)
                .reason("보안이 걱정돼요")
                .build();
        var savedEntityId = service.create(model);

        // when
        var actual = service.get(savedEntityId);

        // then
        assertThat(actual.reason()).isEqualTo(model.reason());
    }
}
