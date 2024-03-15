package com.doonutmate.member.service

import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.service.strategy.MemberDeleteStrategy
import org.springframework.stereotype.Service

@Service
class MemberAppService(
    memberDeleteStrategy: List<MemberDeleteStrategy>,
) {
    private val memberDeleteStrategyMap = memberDeleteStrategy.associateBy { it.oauthTypeStrategy }
    fun delete(req: DeleteRequest) {
        memberDeleteStrategyMap[req.oauthTypeStrategy]
            ?.delete(req)
    }
}
