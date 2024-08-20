package com.doonutmate.doonut.member.service;

import com.doonutmate.doonut.member.entity.MemberEntity;
import com.doonutmate.doonut.member.entity.OauthInfo;
import com.doonutmate.doonut.member.entity.ProfileImageEntity;
import com.doonutmate.doonut.member.model.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class MemberBusinessProfileServiceTest {

    private static final ImageType REPRESENTATIVE = ImageType.REPRESENTATIVE;
    private static final String EXPECTED_URL = "expected url";
    private static final String EXPECTED_NAME = "expected name";


    @DisplayName("회원은 이름, 프로필을 수정할수 있다")
    @Test
    void updateProfile() {
        //given
        MemberEntity member = generateMember();
        ProfileImageEntity profileImage = generateProfileEntity(3L, EXPECTED_URL, REPRESENTATIVE, member, false);
        List<ProfileImageEntity> actualList = generateProfileList();

        //when
        actualList.add(profileImage);
        member.updateNameAndProfileImage(EXPECTED_NAME, actualList);

        //then
        assertEquals(EXPECTED_URL, member.getProfileImages().get(member.getProfileImages().size() - 1).getImageUrl());
        assertEquals(EXPECTED_NAME, member.getName());
    }

    @DisplayName("deleted== false && ImageType.REPRESENTATIVE 프로필 이미지가 모두 소프트 딜리트 되어야 한다.")
    @Test
    void deleteRepresentativeImage() {
        // given
        List<ProfileImageEntity> actualList = generateProfileList();

        // when
        actualList.forEach(profileImage -> {
            if (profileImage.getImageType().equals(ImageType.REPRESENTATIVE) && !profileImage.isDeleted()) {
                profileImage.delete();
            }
        });

        // then
        boolean allDeleted = actualList.stream()
                .filter(profileImage -> profileImage.getImageType().equals(ImageType.REPRESENTATIVE))
                .allMatch(ProfileImageEntity::isDeleted);

        assertTrue(allDeleted, "삭제되지 않은 이미지가 있습니다");
    }

    private List<ProfileImageEntity> generateProfileList() {
        List<ProfileImageEntity> profileList = new ArrayList<>();
        profileList.add(generateProfileEntity(1L, EXPECTED_URL, REPRESENTATIVE, generateMember(), false));
        profileList.add(generateProfileEntity(2L, EXPECTED_URL, REPRESENTATIVE, generateMember(), false));
        return profileList;
    }

    private ProfileImageEntity generateProfileEntity(Long id, String imageUrl, ImageType imageType, MemberEntity member, boolean deleted) {
        return ProfileImageEntity.builder()
                .id(id)
                .imageUrl(imageUrl)
                .imageType(imageType)
                .member(member)
                .deleted(deleted)
                .build();
    }

    private MemberEntity generateMember() {
        return MemberEntity.builder()
                .name("jickchan")
                .email("jickchan0117@naver.com")
                .oauthInfo(OauthInfo.builder().build())
                .build();
    }
}
