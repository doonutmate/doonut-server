package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.model.OauthType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberBusinessServiceTest {

    @Autowired
    private MemberBusinessService service;

    @Test
    void create() {
        // given
        var member = generateMember();

        // when
        var savedEntityId = service.create(member);

        // then
        var saved = service.get(savedEntityId);
        assertThat(saved.name()).isEqualTo(member.name());
    }

    @Test
    void get() {
        // given
        var member = generateMember();
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
        var member = generateMember();
        service.create(member);

        // when
        var actual = service.getByOauthId(oauthId);

        // then
        assertThat(actual)
                .extracting("name", "oauthId")
                .containsExactly("yeongun", oauthId);
    }

    private Member generateMember() {
        var oauthId = "1608565324";
        return Member.builder()
                .name("yeongun")
                .email("yeongun@naver.com")
                .oauthId(oauthId)
                .oauthType(OauthType.KAKAO)
                .build();
    }
}
