package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.model.OauthType.*
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.util.OauthProvider
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val memberBusinessService: MemberBusinessService,
    private val kakaoOauthProvider: KakaoOauthProvider,
) {

    fun login(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member? {
        val savedId = oauthConvert(oauthType).getUserId(tokenRequest)
        return memberBusinessService.getByOauthId(savedId.findMemberId())
            ?: signUpNewMember(tokenRequest, oauthType)
    }

    fun signUpNewMember(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member {
        val savedInfo = oauthConvert(oauthType).getUserInfo(tokenRequest)
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

    private fun oauthConvert(oauthType: OauthType): OauthProvider {
        return when (oauthType) {
            KAKAO -> kakaoOauthProvider
            APPLE -> TODO()
        }
    }
}
