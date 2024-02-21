package com.doonutmate.doonut.image.service;

import com.doonutmate.doonut.image.mapper.ImageMapper;
import com.doonutmate.doonut.image.model.Image;
import com.doonutmate.doonut.image.repository.ImageRepository;
import com.doonutmate.doonut.member.entity.MemberEntity;
import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ImageBusinessService {

    private final ImageRepository repository;
    private final ImageMapper mapper;

    @Transactional
    public Long create(Image image) {
        var newEntity = mapper.toEntity(image);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Image get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }
}
