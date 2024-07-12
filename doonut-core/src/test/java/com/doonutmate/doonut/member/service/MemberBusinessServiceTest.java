package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.calendar.model.Calendar;
import com.doonutmate.doonut.calendar.service.CalendarBusinessService;
import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.challenge.service.ChallengeBusinessService;
import com.doonutmate.doonut.member.event.MemberDeleteEvent;
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

    @Autowired
    private ChallengeBusinessService challengeBusinessService;

    @Autowired
    private CalendarBusinessService calendarBusinessService;

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

    @Test
    void deleteMember() {

        // given
        Member member = generateMember();
        var savedMemberId = service.create(member);

        Challenge challenge = generateChallenge(savedMemberId);
        var savedChallengeId = challengeBusinessService.create(challenge);

        Calendar calendar = generateCalendar(savedMemberId);
        var savedCalendarId = calendarBusinessService.create(calendar);

        // when
        service.delete(savedMemberId);


        // then
        var actualMember = service.get(savedMemberId);
        assertThat(actualMember.deleted()).isTrue();

        var actualChallenge = challengeBusinessService.get(savedChallengeId);
        assertThat(actualChallenge.deleted()).isTrue();

        var actualCalendar = calendarBusinessService.get(savedCalendarId);
        assertThat(actualCalendar.deleted()).isTrue();
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
                .marketingReceiveConsentUpdatedAt(null)
                .build();
    }

    private Challenge generateChallenge(Long memberId) {
        return Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(false)
                .build();
    }

    private Calendar generateCalendar(Long memberId) {
        return Calendar.builder()
                .memberId(memberId)
                .calendarName("캘린더명")
                .totalCount(10)
                .firstUploadedAt(Instant.now())
                .lastUploadedAt(Instant.now())
                .deleted(false)
                .build();
    }
}
