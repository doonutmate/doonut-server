package com.doonutmate.oauth.service

import com.doonutmate.doonut.calendar.model.Calendar
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.model.OauthType.KAKAO
import com.doonutmate.doonut.member.model.OauthType.APPLE
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.JwtTokenProvider
import com.doonutmate.oauth.apple.service.AppleOauthProvider
import com.doonutmate.oauth.controller.dto.LoginRequest
import com.doonutmate.oauth.controller.dto.LoginResponse
import com.doonutmate.oauth.kakao.service.KakaoOauthProvider
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val memberBusinessService: MemberBusinessService,
    private val calendarBusinessService: CalendarBusinessService,
    private val kakaoOauthProvider: KakaoOauthProvider,
    private val appleOauthProvider: AppleOauthProvider,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    fun login(loginRequest: LoginRequest, oauthType: OauthType): LoginResponse {
        val savedId = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.getUserId(loginRequest).id
            }

            APPLE -> {
                appleOauthProvider.getUserId(loginRequest).oauthId
            }
        }
        val memberId = memberBusinessService.getByOauthId(savedId)?.id
            ?: signUp(loginRequest, oauthType).also {
                createDefaultCalendar(it)
            }

        return LoginResponse(jwtTokenProvider.createToken(memberId.toString()), oauthType)
    }

    fun signUp(loginRequest: LoginRequest, oauthType: OauthType): Long {
        val memberId = when (oauthType) {
            KAKAO -> {
                kakaoOauthProvider.signUp(loginRequest)
            }

            APPLE -> {
                appleOauthProvider.signUp(loginRequest)
            }
        }
        return memberId
    }

    private fun createDefaultCalendar(memberId: Long) {
        val fetchedMember: Member = memberBusinessService.get(memberId)

        val defaultCalendar = Calendar.createDefaultCalendar(memberId, fetchedMember.name)
        calendarBusinessService.create(defaultCalendar)
    }
}
