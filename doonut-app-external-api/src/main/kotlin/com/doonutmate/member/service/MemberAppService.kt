package com.doonutmate.member.service

import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.controller.dto.MyPageResponse
import com.doonutmate.member.service.strategy.MemberDeleteStrategy
import org.springframework.stereotype.Service

@Service
class MemberAppService(
    memberDeleteStrategy: List<MemberDeleteStrategy>,
    private val memberBusinessService: MemberBusinessService,
) {
    private val memberDeleteStrategyMap = memberDeleteStrategy.associateBy { it.oauthType }
    fun delete(req: DeleteRequest) {
        memberDeleteStrategyMap[req.oauthType]
            ?.delete(req)
    }

    fun findMyInfo(memberId: Long): MyPageResponse {
        val member = memberBusinessService.get(memberId)
        requireNotNull(member) { "해당하는 멤버가 없습니다. memberId: $memberId" }

        val profileImageUrl = member.profileImages?.getOrNull(0)?.imageUrl

        return MyPageResponse(nickname = member.name, profileImageUrl = profileImageUrl)
    }
}
