package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import org.springframework.stereotype.Service

@Service
class KakaoOauthLoginService(
    private val kakaoAccessClientLoginService: KakaoAccessClientLoginService,
    private val memberBusinessService: MemberBusinessService,
) {
    fun kakaoLoginPreventDuplication(
        oauthType: OauthType,
        tokenIdResponse: TokenIdResponse,
    ): Boolean {
        return true
    }
}
