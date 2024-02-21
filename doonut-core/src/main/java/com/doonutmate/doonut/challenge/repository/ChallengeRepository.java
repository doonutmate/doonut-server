package com.doonutmate.doonut.challenge.repository;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import com.doonutmate.doonut.image.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {


    @Query("""
                SELECT challenge
                FROM ChallengeEntity challenge
                WHERE challenge.deleted = false 
                AND challenge.memberId = :memberId
                AND FUNCTION('YEAR', challenge.createdAt) = :year
                AND FUNCTION('MONTH', challenge.createdAt) = :month
            """)
    Optional<List<ChallengeEntity>> findAllByMemberIdAndDate(
            @Param("memberId") String memberId,
            @Param("year") int year,
            @Param("month") int month
    );
}
