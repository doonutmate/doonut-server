package com.doonutmate.doonut.challenge.service;

import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.util.CommonDateUtils;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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
        service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
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
        service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
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
        service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
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
        var savedId1 = service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
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
        var savedId1 = service.create(Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
        var marchLastDay = CommonDateUtils.getInstant(2024, 3, 31);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, marchLastDay, savedId1);

        var savedId2 = service.create(Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
        var aprilFirstDay = CommonDateUtils.getFirst(2024, 4);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, aprilFirstDay, savedId2);

        var savedId3 = service.create(Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(false)
                .build());
        var aprilSecondDay = CommonDateUtils.getInstant(2024, 4, 2);
        jdbcTemplate.update("""
                    UPDATE challenge
                    SET created_at = ?
                    WHERE id = ?
                """, aprilSecondDay, savedId3);

        var savedId4 = service.create(Challenge.builder()
                .memberId(memberId)
                .imageUrl("https://test.url")
                .deleted(true)
                .build());
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
}
