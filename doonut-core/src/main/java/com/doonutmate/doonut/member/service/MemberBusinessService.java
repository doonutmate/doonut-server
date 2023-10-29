package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.mapper.MemberMapper;
import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberBusinessService {

    private final MemberRepository repository;

    private final MemberMapper mapper;

    @Transactional
    public Long create(Member member) {
        var newEntity = mapper.toEntity(member);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Member get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }
}
