package com.doonutmate.oauth.common

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.model.OauthType.*
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.JwtTokenProvider
import com.doonutmate.oauth.kakao.service.KakaoOauthProvider
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val memberBusinessService: MemberBusinessService,
    private val kakaoOauthProvider: KakaoOauthProvider,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun login(tokenRequest: TokenRequest, oauthType: OauthType): String? {
        val savedId = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.getUserId(tokenRequest)
            }
            APPLE -> TODO("애플 기능 추가시")
        }
        val userId = memberBusinessService.getByOauthId(savedId.toString())?.id
            ?: signUp(tokenRequest, oauthType)

        return jwtTokenProvider.createToken(userId.toString())
    }

    fun signUp(tokenRequest: TokenRequest, oauthType: OauthType): Long {
        val newMemberId = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.signUp(tokenRequest)
            }
            APPLE -> TODO("애플 기능 추가시")
        }
        return newMemberId
    }
}
