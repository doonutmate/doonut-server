package com.doonutmate.member.service.strategy

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.oauth.apple.service.AppleOauthProvider
import org.springframework.stereotype.Service

@Service
class AppleMemberDelete(
    private val appleOauthProvider: AppleOauthProvider,
    private val memberBusinessService: MemberBusinessService,
) : MemberDeleteStrategy {

    override val oauthType: OauthType
        get() = OauthType.APPLE

    override fun delete(req: DeleteRequest) {
        val accessToken = appleOauthProvider.createAuthToken(req.code).accessToken
        appleOauthProvider.revokeAccessToken(accessToken)
        memberBusinessService.delete(req.memberId)
    }
}
