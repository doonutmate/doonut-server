package com.doonutmate.doonut.challenge.service;

import com.doonutmate.doonut.challenge.mapper.ChallengeMapper;
import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.challenge.repository.ChallengeRepository;
import com.doonutmate.doonut.image.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChallengeBusinessServicee {
    private final ChallengeRepository repository;
    private final ChallengeMapper mapper;

    @Transactional
    public Long create(Challenge challenge) {
        var newEntity = mapper.toEntity(challenge);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Challenge get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }

}
