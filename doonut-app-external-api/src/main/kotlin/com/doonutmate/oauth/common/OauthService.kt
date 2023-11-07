package com.doonutmate.oauth.common

import com.doonutmate.doonut.member.model.Member
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

    fun login(tokenRequest: TokenRequest, oauthType: OauthType): String {
        val savedId = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.getUserId(tokenRequest)
            }
            APPLE -> TODO("애플 기능 추가시")
        }
        return generateToken(savedId.id, tokenRequest, oauthType)
    }

    fun signUp(tokenRequest: TokenRequest, oauthType: OauthType): Member {
        val newMember = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.signUp(tokenRequest)
            }
            APPLE -> TODO("애플 기능 추가시")
        }
        return newMember
    }

    fun generateToken(savedId: String, tokenRequest: TokenRequest, oauthType: OauthType): String {
        return memberBusinessService.getByOauthId(savedId)?.run {
            jwtTokenProvider.createToken(savedId)
        } ?: run {
            val newMember = signUp(tokenRequest, oauthType)
            jwtTokenProvider.createToken(newMember.id.toString())
        }
    }
}
