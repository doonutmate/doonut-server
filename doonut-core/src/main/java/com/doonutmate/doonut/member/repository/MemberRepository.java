package com.doonutmate.doonut.member.repository;

import com.doonutmate.doonut.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    @Query("""
                SELECT member
                FROM MemberEntity member
                WHERE member.deleted = false AND member.oauthInfo.oauthId = :oauthId
            """)
    Optional<MemberEntity> findByOauthId(String oauthId);

    @Query("""
                SELECT member.fcmToken
                FROM MemberEntity member
                WHERE member.deleted= false AND member.serviceAlarm = true AND member.fcmToken IS NOT NULL
            """)
    List<String> findByFcmTokenList();

    @Query("""
                SELECT member.name
                FROM MemberEntity member
                WHERE member.deleted = false AND member.fcmToken = :fcmToken
            """)
    String findByName(String fcmToken);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MemberEntity m SET m.fcmToken = :deviceToken WHERE  m.id = :memberId")
    void updateDeviceToken(String deviceToken, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MemberEntity m SET m.name = :newName WHERE m.id = :memberId")
    void updateMemberNameByMemberId(String newName, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MemberEntity m SET m.serviceAlarm = :serviceAlarm WHERE m.id = :memberId")
    void updateServiceAlarmConfig(boolean serviceAlarm, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MemberEntity m SET m.lateNightAlarm = :lateNightAlarm WHERE m.id = :memberId")
    void updateLateNightAlarm(boolean lateNightAlarm, Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MemberEntity m SET m.marketingReceiveConsent = :marketingReceiveConsent, m.marketingReceiveConsentUpdatedAt = :marketingReceiveConsentUpdatedAt WHERE m.id = :memberId")
    void updateMarketingReceiveConsent(boolean marketingReceiveConsent, Instant marketingReceiveConsentUpdatedAt, Long memberId);
}
