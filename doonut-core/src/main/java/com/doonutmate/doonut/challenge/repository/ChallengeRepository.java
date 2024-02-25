package com.doonutmate.doonut.challenge.repository;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {

    @Query("""
                SELECT challenge
                FROM ChallengeEntity challenge
                WHERE challenge.deleted = false
                AND challenge.memberId = :memberId
                AND challenge.createdAt >= :startAt
                AND challenge.createdAt <= :endAt
            """)
    List<ChallengeEntity> findAllByMemberIdAndDate(
            @Param("memberId") Long memberId,
            @Param("startAt") Instant startAt,
            @Param("endAt") Instant endAt
    );
}
