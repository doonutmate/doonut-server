package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.mapper.MemberDeleteReasonMapper;
import com.doonutmate.doonut.member.model.MemberDeleteReason;
import com.doonutmate.doonut.member.repository.MemberDeleteReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberDeleteReasonBusinessService {

    private final MemberDeleteReasonRepository repository;
    private final MemberDeleteReasonMapper mapper;

    @Transactional
    public Long create(MemberDeleteReason model) {
        var newEntity = mapper.toEntity(model);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public MemberDeleteReason get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }
}
