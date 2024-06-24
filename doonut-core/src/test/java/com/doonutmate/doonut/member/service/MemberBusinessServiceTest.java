package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.model.OauthType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

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

    @Test
    void updateServiceAlarmConfig() {
        // given
        var member = generateMember();
        var savedEntityId = service.create(member);

        // when
        service.updateServiceAlarmConfig(true, savedEntityId);

        // then
        var actual = service.get(savedEntityId);
        assertThat(actual.serviceAlarm()).isTrue();
    }

    @Test
    void updateLateNightAlarm() {
        // given
        var member = generateMember();
        var savedEntityId = service.create(member);

        // when
        service.updateLateNightAlarm(true, savedEntityId);

        // then
        var actual = service.get(savedEntityId);
        assertThat(actual.lateNightAlarm()).isTrue();
    }

    @Test
    void updateMarketingReceiveConsent() {
        // given
        var member = generateMember();
        var savedEntityId = service.create(member);

        // when
        service.updateMarketingReceiveConsent(true, savedEntityId);

        // then
        var actual = service.get(savedEntityId);
        assertThat(actual.marketingReceiveConsent()).isTrue();
    }

    private Member generateMember() {
        var oauthId = "1608565324";
        return Member.builder()
                .name("yeongun")
                .email("yeongun@naver.com")
                .oauthId(oauthId)
                .oauthType(OauthType.KAKAO)
                .serviceAlarm(true)
                .lateNightAlarm(true)
                .marketingReceiveConsent(true)
                .marketingReceiveConsentUpdatedAt(Instant.now())
                .build();
    }
}
