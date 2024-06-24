package com.doonutmate.member.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.MemberDeleteReason
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.doonut.member.service.MemberDeleteReasonBusinessService
import com.doonutmate.member.controller.dto.AlarmConfigResponse
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.controller.dto.MyPageResponse
import com.doonutmate.member.service.strategy.MemberDeleteStrategy
import com.doonutmate.util.CommonDateUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class MemberAppService(
    memberDeleteStrategy: List<MemberDeleteStrategy>,
    private val memberBusinessService: MemberBusinessService,
    private val deleteReasonBusinessService: MemberDeleteReasonBusinessService,
) {
    private val memberDeleteStrategyMap = memberDeleteStrategy.associateBy { it.oauthType }

    @Transactional
    fun delete(req: DeleteRequest, reason: String?) {
        memberDeleteStrategyMap[req.oauthType]
            ?.delete(req)
        deleteReasonBusinessService.create(MemberDeleteReason.of(req.memberId, reason))
    }

    fun findMyInfo(memberId: Long): MyPageResponse {
        val member = getMember(memberId)

        val profileImageUrl = member.profileImages?.lastOrNull()?.imageUrl

        return MyPageResponse(nickname = member.name, profileImageUrl = profileImageUrl)
    }

    fun findMyAlarmConfig(memberId: Long): AlarmConfigResponse {
        val member = getMember(memberId)

        return AlarmConfigResponse(
            serviceAlarm = member.serviceAlarm,
            lateNightAlarm = member.lateNightAlarm,
            marketingReceiveConsent = member.marketingReceiveConsent,
            marketingReceiveConsentUpdatedAt = timeFormatConvert(member.marketingReceiveConsentUpdatedAt)
        )
    }

    private fun timeFormatConvert(instant: Instant): String {
        val localDateTime = CommonDateUtils.convertInstantToLocalDateTime(instant)
        return CommonDateUtils.changeTimeFormat(localDateTime)
    }

    fun updateServiceAlarmConfig(memberId: Long, serviceAlarm: Boolean) {
        getMember(memberId)
        memberBusinessService.updateServiceAlarmConfig(serviceAlarm, memberId)
    }

    fun updateLateNightAlarm(memberId: Long, lateNightAlarm: Boolean) {
        getMember(memberId)
        memberBusinessService.updateLateNightAlarm(lateNightAlarm, memberId)
    }

    fun updateMarketingReceiveConsent(memberId: Long, marketingReceiveConsent: Boolean) {
        getMember(memberId)
        memberBusinessService.updateMarketingReceiveConsent(marketingReceiveConsent, memberId)
    }

    private fun getMember(memberId: Long): Member {
        val member = memberBusinessService.get(memberId)
        requireNotNull(member) { "해당하는 멤버가 없습니다. memberId: $memberId" }

        return member
    }
}
