package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.mapper.MemberMapper;
import com.doonutmate.doonut.member.mapper.ProfileImageMapper;
import com.doonutmate.doonut.member.model.ImageType;
import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.model.ProfileImage;
import com.doonutmate.doonut.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberBusinessService {

    private final MemberRepository repository;

    private final MemberMapper mapper;
    private final ProfileImageMapper profileImageMapper;

    @Transactional
    public Long create(Member member) {
        var newEntity = mapper.toEntity(member);

        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }


    @Transactional
    public Long create(Member member, String imageUrl) {
        var newEntity = mapper.toEntity(member);

        var profileImageEntity = profileImageMapper.toEntity(ProfileImage.builder()
                .imageType(ImageType.REPRESENTATIVE)
                .imageUrl(imageUrl)
                .build());

        profileImageEntity.setMember(newEntity);
        newEntity.setProfileImages(List.of(profileImageEntity));
        var savedEntity = repository.save(newEntity);

        return savedEntity.getId();
    }

    public Member get(Long id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElse(null);
    }

    public Member getByOauthId(String OauthId) {
        return repository.findByOauthId(OauthId)
                .map(mapper::toModel)
                .orElse(null);
    }
}
