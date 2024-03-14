package com.doonutmate.member.service

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.service.strategy.AppleMemberDelete
import com.doonutmate.member.service.strategy.KakaoMemeberDelete
import org.springframework.stereotype.Service

@Service
class MemberAppService(
    private val appleMemberDelete: AppleMemberDelete,
    private val kakaoMemeberDelete: KakaoMemeberDelete,
    private val memberBusinessService: MemberBusinessService,
) {
    fun delete(req: DeleteRequest) {
        when (req.oauthType) {
            OauthType.KAKAO -> {
                kakaoMemeberDelete.delete(req)
            }

            OauthType.APPLE -> {
                appleMemberDelete.delete(req)
            }
        }
    }
}
