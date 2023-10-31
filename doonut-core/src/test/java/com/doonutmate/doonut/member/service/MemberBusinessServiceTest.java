package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.model.OauthType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberBusinessServiceTest {

    @Autowired
    private MemberBusinessService service;

    @Test
    void create() {
        // given
        var member = Member.builder()
                .name("yeongun")
                .build();

        // when
        var savedEntityId = service.create(member);

        // then
        var saved = service.get(savedEntityId);
        assertThat(saved.name()).isEqualTo(member.name());
    }

    @Test
    void get() {
        // given
        var member = Member.builder()
                .name("yeongun")
                .build();
        var savedEntityId = service.create(member);

        // when
        var actual = service.get(savedEntityId);

        // then
        assertThat(actual)
                .extracting("id", "name")
                .containsExactly(savedEntityId, "yeongun");
    }

    @Test
    void getByOauthId() {
        // given
        var oauthId = "1608565324";
        var member = Member.builder()
                .name("yeongun")
                .oauthId(oauthId)
                .oauthType(OauthType.KAKAO)
                .build();
        service.create(member);

        // when
        var actual = service.getByOauthId(oauthId);

        // then
        assertThat(actual)
                .extracting("name", "oauthId")
                .containsExactly("yeongun", oauthId);
    }
}
