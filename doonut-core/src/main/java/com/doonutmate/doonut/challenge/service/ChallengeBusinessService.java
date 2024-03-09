package com.doonutmate.doonut.challenge.service;

import com.doonutmate.doonut.challenge.mapper.ChallengeMapper;
import com.doonutmate.doonut.challenge.model.Challenge;
import com.doonutmate.doonut.challenge.repository.ChallengeRepository;
import com.doonutmate.doonut.member.event.MemberDeleteEvent;
import com.doonutmate.util.CommonDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChallengeBusinessService {
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

    public List<Challenge> getAllByIdAndDate(Long memberId, int year, int month) {
        var startAt = CommonDateUtils.getFirst(year, month);
        var endAt = CommonDateUtils.getLast(year, month);

        return repository.findAllByMemberIdAndDate(memberId, startAt, endAt)
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    public List<Challenge> getList(Long memberId, Instant startAt) {
        return repository.findAllByMemberIdAfter(memberId, startAt)
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow();
        entity.delete();
    }

    @EventListener
    public void deleteByEvent(MemberDeleteEvent event) {
        var memberId = event.id();
        repository.deleteAllByMemberId(memberId);
    }
}
