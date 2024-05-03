package com.doonutmate.doonut.member.repository;

import com.doonutmate.doonut.member.entity.MemberEntity;
import com.doonutmate.doonut.member.entity.OauthInfo;
import com.doonutmate.doonut.member.model.OauthType;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    void findByOauthId() {
        // given
        var oauthId = "1608565323";
        var oauthInfo = OauthInfo.builder()
                .oauthId(oauthId)
                .oauthType(OauthType.KAKAO)
                .build();
        var entity = MemberEntity.builder()
                .name("yeongun")
                .email("yeongun@naver.com")
                .oauthInfo(OauthInfo.builder()
                        .oauthId(oauthInfo.getOauthId())
                        .oauthType(oauthInfo.getOauthType())
                        .build())
                .build();
        repository.save(entity);

        // when
        var actual = repository.findByOauthId(oauthId)
                .orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(actual)
                .extracting("name", "oauthInfo")
                .containsExactly("yeongun", oauthInfo);
    }
}
