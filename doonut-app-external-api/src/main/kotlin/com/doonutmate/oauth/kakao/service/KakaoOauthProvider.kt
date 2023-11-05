package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.externalOauth.OauthProvider
import com.doonutmate.oauth.kakao.client.KakaoAccessClient
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import com.doonutmate.oauth.kakao.dto.TokenRequest
import org.springframework.stereotype.Service

@Service
class KakaoOauthProvider(
    private val kakaoAccessClient: KakaoAccessClient,
    private val memberBusinessService: MemberBusinessService,
) : OauthProvider() {

    override fun getUserId(tokenRequest: TokenRequest): TokenIdResponse {
        return kakaoAccessClient.getKakaoUserId("Bearer ${tokenRequest.accessToken}")
    }

    override fun getUserInfo(tokenRequest: TokenRequest): KakaoInfoResponse {
        return kakaoAccessClient.getKakaoUserInfo("Bearer ${tokenRequest.accessToken}")
    }

    fun signUpKakao(tokenRequest: TokenRequest): Member {
        val savedInfo = getUserInfo(tokenRequest)
        val newMember = Member.builder()
            .name(savedInfo.kakao_account?.name)
            .email(savedInfo.kakao_account?.email)
            .oauthId(savedInfo.id)
            .oauthType(OauthType.KAKAO)
            .deleted(false)
            .build()
        memberBusinessService.create(newMember)
        return newMember
    }
}
