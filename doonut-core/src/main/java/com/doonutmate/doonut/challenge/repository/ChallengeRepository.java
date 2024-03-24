package com.doonutmate.doonut.challenge.repository;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("""
                SELECT challenge
                FROM ChallengeEntity challenge
                WHERE challenge.deleted = false
                AND challenge.memberId = :memberId
                AND challenge.createdAt >= :startAt
            """)
    List<ChallengeEntity> findAllByMemberIdAfter(Long memberId, Instant startAt);

    @Modifying
    @Query("""
                UPDATE ChallengeEntity c
                SET c.deleted = true
                WHERE c.memberId = :memberId
            """)
    void deleteAllByMemberId(Long memberId);

    /**
     *
     * 3초 동안 락을 획득하지 못하면 0을 반환한다.
     * @return
     * 1 : 잠금을 획득하는데 성공하였을때
     * 0 : 3초 동안 잠금 획득에 실패했을때
     */
    @Query(value = "SELECT GET_LOCK(:key, 3)", nativeQuery = true)
    int getLock(@Param("key") String key);

    @Query(value = "SELECT RELEASE_LOCK(:key)", nativeQuery = true)
    void releaseLock(@Param("key") String key);
}

