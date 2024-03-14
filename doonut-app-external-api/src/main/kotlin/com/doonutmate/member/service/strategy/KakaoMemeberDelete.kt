package com.doonutmate.member.service.strategy

import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.member.controller.dto.DeleteRequest
import org.springframework.stereotype.Service

@Service
class KakaoMemeberDelete(
    private val memberBusinessService: MemberBusinessService,
) : StrategyMemberDelete {
    override fun delete(req: DeleteRequest) {
        memberBusinessService.delete(req.memberId)
    }
}
