package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.controller.dto.LoginRequest
import com.doonutmate.oauth.kakao.client.KakaoAccessClient
import com.doonutmate.oauth.kakao.dto.KakaoIdResponse
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.service.OauthProvider
import org.springframework.stereotype.Service

@Service
class KakaoOauthProvider(
    private val kakaoAccessClient: KakaoAccessClient,
    private val memberBusinessService: MemberBusinessService,
) : OauthProvider<KakaoIdResponse, KakaoInfoResponse> {

    override fun getUserId(loginRequest: LoginRequest): KakaoIdResponse {
        return kakaoAccessClient.getKakaoUserId("Bearer ${loginRequest.accessToken}")
    }

    override fun getUserInfo(loginRequest: LoginRequest): KakaoInfoResponse {
        return kakaoAccessClient.getKakaoUserInfo("Bearer ${loginRequest.accessToken}")
    }

    override fun signUp(loginRequest: LoginRequest): Long {
        val savedInfo = getUserInfo(loginRequest)
        val newMember = Member.builder()
            .name(savedInfo.kakao_account?.name)
            .email(savedInfo.kakao_account?.email)
            .oauthId(savedInfo.id)
            .oauthType(OauthType.KAKAO)
            .deleted(false)
            .build()
        return memberBusinessService.create(newMember)
    }
}
