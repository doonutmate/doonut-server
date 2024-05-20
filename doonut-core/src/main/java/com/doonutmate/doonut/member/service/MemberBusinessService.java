package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.entity.MemberEntity;
import com.doonutmate.doonut.member.entity.ProfileImageEntity;
import com.doonutmate.doonut.member.event.MemberDeleteEvent;
import com.doonutmate.doonut.member.mapper.MemberMapper;
import com.doonutmate.doonut.member.mapper.ProfileImageMapper;
import com.doonutmate.doonut.member.model.ImageType;
import com.doonutmate.doonut.member.model.Member;
import com.doonutmate.doonut.member.model.ProfileImage;
import com.doonutmate.doonut.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberBusinessService {

    private final MemberRepository repository;
    private final ApplicationEventPublisher eventPublisher;

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

    @Transactional
    public Long updateMemberProfile(Long memberId, String name, String imageUrl) {
        MemberEntity newMemberEntity = mapper.toEntity(get(memberId));
        List<ProfileImageEntity> updatedProfileImages = updateRepresentativeImageUrl(newMemberEntity.getProfileImages(), imageUrl);

        newMemberEntity.updateMyPage(name, updatedProfileImages);
        Member savedMember = mapper.toModel(newMemberEntity);
        return create(savedMember);
    }

    private List<ProfileImageEntity> updateRepresentativeImageUrl(List<ProfileImageEntity> profileImages, String imageUrl) {
        return profileImages.stream()
                .map(profileImageEntity -> {
                    if (profileImageEntity.getImageType().equals(ImageType.REPRESENTATIVE)) {
                        profileImageEntity.setImageUrl(imageUrl);
                    }
                    return profileImageEntity;
                })
                .collect(Collectors.toList());
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


    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(RuntimeException::new); // TODO 적절한 예외로 변환 ex) NotFoundMemberException

        entity.delete();
        eventPublisher.publishEvent(new MemberDeleteEvent(id));
    }
}
