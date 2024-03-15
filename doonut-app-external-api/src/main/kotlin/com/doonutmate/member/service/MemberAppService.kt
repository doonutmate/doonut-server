package com.doonutmate.member.service

import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.service.strategy.MemberDeleteStrategy
import org.springframework.stereotype.Service

@Service
class MemberAppService(
    private val memberDeleteStrategy: Map<String, MemberDeleteStrategy>,
) {
    fun delete(req: DeleteRequest) {
        memberDeleteStrategy[req.oauthTypeStrategy.strategyName]
            ?.delete(req)
    }
}
