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

import java.util.ArrayList;
import java.util.List;

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
        var fetchedEntity = mapper.toEntity(member);

        var savedEntity = repository.save(fetchedEntity);

        return savedEntity.getId();
    }

    @Transactional
    public Long create(Member member, String imageUrl) {
        var fetchedEntity = mapper.toEntity(member);

        var profileImageEntity = createRepresentativeProfileImage(imageUrl, fetchedEntity);

        fetchedEntity.setProfileImages(List.of(profileImageEntity));

        List<ProfileImageEntity> profileImages = new ArrayList<>();
        profileImages.add(profileImageEntity);
        fetchedEntity.setProfileImages(profileImages);

        var savedEntity = repository.save(fetchedEntity);

        return savedEntity.getId();
    }

    @Transactional
    public Long updateProfile(Long memberId, String name, String imageUrl) {
        MemberEntity fetchedEntity = getEntity(memberId);
        List<ProfileImageEntity> profileImages = deleteRepresentativeImage(fetchedEntity.getProfileImages());

        var profileImageEntity = createRepresentativeProfileImage(imageUrl, fetchedEntity);
        profileImages.add(profileImageEntity);

        fetchedEntity.updateNameAndProfileImage(name, profileImages);

        var savedEntity = repository.save(fetchedEntity);

        return savedEntity.getId();
    }

    private List<ProfileImageEntity> deleteRepresentativeImage(List<ProfileImageEntity> profileImages) {
        profileImages.forEach(profileImage -> {
            if (profileImage.getImageType().equals(ImageType.REPRESENTATIVE) && !profileImage.isDeleted()) {
                profileImage.delete();
            }
        });
        return profileImages;
    }

    private ProfileImageEntity createRepresentativeProfileImage(String imageUrl, MemberEntity entity) {
        var profileImage = profileImageMapper.toEntity(ProfileImage.builder()
                .imageType(ImageType.REPRESENTATIVE)
                .imageUrl(imageUrl)
                .build());

        profileImage.setMember(entity);
        return profileImage;
    }

    @Transactional
    public void updateProfileName(String name, Long memberId) {
        repository.updateMemberNameByMemberId(name, memberId);
    }

    public MemberEntity getEntity(Long id) {
        return repository.findById(id)
                .orElse(null);
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
