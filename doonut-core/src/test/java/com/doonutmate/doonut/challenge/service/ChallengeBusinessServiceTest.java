package com.doonutmate.doonut.challenge.service;

import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.member.event.MemberDeleteEvent;
import com.doonutmate.util.CommonDateUtils;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional
@SpringBootTest
class ChallengeBusinessServiceTest {

    @Autowired
    private ChallengeBusinessService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager em;

    @DisplayName("해당 년 월을 조건으로 넣으면 1일을 조회할 수 있다.")
    @Test
    void getAllByIdAndDate_firstDay() {
        // given
        service.create(createChallenge(1L));
        var createdAt = CommonDateUtils.getInstant(2024, 2, 1);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE member_id = ?
                """, createdAt, 1L);
        em.flush();
        em.clear();

        // when
        var actual = service.getAllByIdAndDate(1L, 2024, 2);

        // then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).createdAt()).isEqualTo(createdAt);
    }

    @DisplayName("해당 년 월을 조건으로 넣으면 마지막 일을 조회할 수 있다.")
    @Test
    void getAllByIdAndDate_lastDay() {
        // given
        service.create(createChallenge(1L));
        var createdAt = CommonDateUtils.getLast(2024, 2);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE member_id = ?
                """, createdAt, 1L);
        em.flush();
        em.clear();

        // when
        var actual = service.getAllByIdAndDate(1L, 2024, 2);

        // then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).createdAt()).isEqualTo(createdAt);
    }

    @DisplayName("해당 년 월을 조건으로 넣으면 다음 달의 1일은 조회하지 않는다.")
    @Test
    void getAllByIdAndDate_nextFirstDay() {
        // given
        service.create(createChallenge(1L));
        var createdAt = CommonDateUtils.getFirst(2024, 3);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE member_id = ?
                """, createdAt, 1L);
        em.flush();
        em.clear();

        // when
        var actual = service.getAllByIdAndDate(1L, 2024, 2);

        // then
        assertThat(actual).isEmpty();
    }

    @DisplayName("get 메서드를 호출하면 인자로 받은 id에 해당하는 챌린지를 반환한다.")
    @Test
    void get() {
        // given
        var savedId1 = service.create(createChallenge(1L));
        em.flush();
        em.clear();

        // when
        var result = service.get(savedId1);

        // then
        assertThat(result.imageUrl()).isEqualTo("https://test.url");
    }

    @DisplayName("memberId와 startAt을 인자로 받으면 조건에 맞는 챌린지들을 반환한다.")
    @Test
    void getList() {

        // given
        var memberId = 1L;
        var savedId1 = service.create(createChallenge(memberId));
        var marchLastDay = CommonDateUtils.getInstant(2024, 3, 31);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, marchLastDay, savedId1);

        var savedId2 = service.create(createChallenge(memberId));
        var aprilFirstDay = CommonDateUtils.getFirst(2024, 4);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, aprilFirstDay, savedId2);

        var savedId3 = service.create(createChallenge(memberId));
        var aprilSecondDay = CommonDateUtils.getInstant(2024, 4, 2);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, aprilSecondDay, savedId3);

        var savedId4 = service.create(createChallenge(memberId, true));
        var aprilThirdDay = CommonDateUtils.getInstant(2024, 4, 3);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, aprilThirdDay, savedId4);
        em.flush();
        em.clear();

        // when
        var result = service.getList(memberId, aprilFirstDay);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).createdAt()).isEqualTo(aprilFirstDay);
        assertThat(result.get(1).createdAt()).isEqualTo(aprilSecondDay);
    }

    @DisplayName("챌린지 id를 받으면 해당 챌린지를 삭제한다.")
    @Test
    void delete() {
        // given
        var memberId = 1L;
        var savedId = service.create(createChallenge(memberId));

        // when
        service.delete(savedId);

        // then
        var deletedMember = service.get(savedId);
        assertThat(deletedMember.deleted()).isTrue();
    }

    @DisplayName("MemberDeleteEvent를 받으면 해당 멤버의 모든 챌린지를 삭제한다.")
    @Test
    void deleteByEvent() {
        // given
        var memberId1 = 1L;
        var memberId2 = 2L;
        var savedId1 = service.create(createChallenge(memberId1));
        var savedId2 = service.create(createChallenge(memberId1));
        var savedId3 = service.create(createChallenge(memberId2));
        em.flush();
        em.clear();

        // when
        service.deleteByEvent(MemberDeleteEvent.builder().id(memberId1).build());

        // then
        assertThat(service.get(savedId1).deleted()).isTrue();
        assertThat(service.get(savedId2).deleted()).isTrue();
        assertThat(service.get(savedId3).deleted()).isFalse();
    }

    @DisplayName("락을 정상적으로 획득하면 1을 반환한다.")
    @Test
    void getLock() {
        // given
        var memberId = 1L;

        // when
        var result = service.getLock(memberId);

        // then
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("락을 정상적으로 반납한다.")
    @Test
    void releaseLock() {
        // given
        var memberId = 2L;
        service.getLock(memberId);

        // when & then
        assertDoesNotThrow(() -> service.releaseLock(memberId));
    }

    private Challenge createChallenge(long memberId) {
        return Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(false)
                .build();
    }

    private Challenge createChallenge(long memberId, boolean deleted) {
        return Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(deleted)
                .build();
    }
}
