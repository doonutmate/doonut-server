package com.doonutmate.oauth.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.model.OauthType.*
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.JwtTokenProvider
import com.doonutmate.oauth.controller.dto.LoginRequest
import com.doonutmate.oauth.controller.dto.LoginResponse
import com.doonutmate.oauth.kakao.service.KakaoOauthProvider
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val memberBusinessService: MemberBusinessService,
    private val kakaoOauthProvider: KakaoOauthProvider,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun login(loginRequest: LoginRequest, oauthType: OauthType): LoginResponse {
        val savedId = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.getUserId(loginRequest)
            }

            APPLE -> TODO("애플 기능 추가시")
        }
        val member = memberBusinessService.getByOauthId(savedId.toString())
            ?: signUp(loginRequest, oauthType)

        return LoginResponse(jwtTokenProvider.createToken(member.oauthId))
    }

    fun signUp(loginRequest: LoginRequest, oauthType: OauthType): Member {
        val newMember = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.signUp(loginRequest)
            }

            APPLE -> TODO("애플 기능 추가시")
        }
        return newMember
    }
}
