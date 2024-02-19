package com.doonutmate.doonut.challenge.repository;

import com.doonutmate.doonut.challenge.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
}
