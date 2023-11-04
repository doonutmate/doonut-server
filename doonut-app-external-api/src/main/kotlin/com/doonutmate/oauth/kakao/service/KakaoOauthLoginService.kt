package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import org.springframework.stereotype.Service

@Service
class KakaoOauthLoginService(
    private val kakaoAccessClientLoginService: KakaoAccessClientLoginService,
    private val memberBusinessService: MemberBusinessService,
) {

    fun Login(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member? {
        val savedId = kakaoAccessClientLoginService.getKakaoUserId(tokenRequest)
        return memberBusinessService.getByOauthId(savedId.findMemberId())
            ?: SignUpNewMember(tokenRequest, oauthType)
    }

    fun SignUpNewMember(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member {
        val savedInfo = kakaoAccessClientLoginService.getKakaoUserInfo(tokenRequest)
        val newMember = Member.builder()
            .name(savedInfo.kakao_account?.name)
            .email(savedInfo.kakao_account?.email)
            .oauthId(savedInfo.id)
            .oauthType(oauthType)
            .deleted(false)
            .build()
        memberBusinessService.create(newMember)
        return newMember
    }
}
