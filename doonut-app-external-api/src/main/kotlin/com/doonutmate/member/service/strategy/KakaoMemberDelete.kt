package com.doonutmate.member.service.strategy

import com.doonutmate.doonut.member.model.OauthTypeStrategy
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.member.controller.dto.DeleteRequest
import org.springframework.stereotype.Service

@Service
class KakaoMemberDelete(
    private val memberBusinessService: MemberBusinessService,
) : MemberDeleteStrategy {

    override val oauthTypeStrategy: OauthTypeStrategy
        get() = OauthTypeStrategy.KAKAO

    override fun delete(req: DeleteRequest) {
        memberBusinessService.delete(req.memberId)
    }
}
