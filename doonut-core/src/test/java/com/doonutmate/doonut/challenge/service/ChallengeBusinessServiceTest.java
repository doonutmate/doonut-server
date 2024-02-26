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
        var createdAt = CommonDateUtils.getInstant(2024, 2, 1);
        service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .createdAt(createdAt)
                .deleted(false)
                .build());
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
        var createdAt = CommonDateUtils.getLast(2024, 2);
        service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .createdAt(createdAt)
                .deleted(false)
                .build());
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
        var createdAt = CommonDateUtils.getFirst(2024, 3);
        service.create(Challenge.builder()
                .memberId(1L)
                .imageUrl("https://test.url")
                .createdAt(createdAt)
                .deleted(false)
                .build());
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
}
