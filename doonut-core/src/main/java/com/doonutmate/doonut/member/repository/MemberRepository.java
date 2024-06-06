package com.doonutmate.doonut.member.repository;

import com.doonutmate.doonut.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("""
                SELECT member
                FROM MemberEntity member
                WHERE member.deleted = false AND member.oauthInfo.oauthId = :oauthId
            """)
    Optional<MemberEntity> findByOauthId(String oauthId);

    @Modifying
    @Query("UPDATE MemberEntity m SET m.name = :newName WHERE m.id = :memberId")
    void updateMemberNameByMemberId(Long memberId, String newName);
}
