package com.doonutmate.member.service

import com.doonutmate.doonut.member.model.MemberDeleteReason
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.doonut.member.service.MemberDeleteReasonBusinessService
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.controller.dto.MyPageResponse
import com.doonutmate.member.service.strategy.MemberDeleteStrategy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
        val member = memberBusinessService.get(memberId)
        requireNotNull(member) { "해당하는 멤버가 없습니다. memberId: $memberId" }

        val profileImageUrl = member.profileImages?.lastOrNull()?.imageUrl

        return MyPageResponse(nickname = member.name, profileImageUrl = profileImageUrl)
    }
}
