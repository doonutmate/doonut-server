package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import javax.lang.model.type.ErrorType

@Service
@Transactional
class KakaoOauthLoginService(
    private val kakaoAccessClientLoginService: KakaoAccessClientLoginService,
    private val memberBusinessService: MemberBusinessService,
) {

    fun kakaoLoginPreventDuplication(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member? {
        val savedId = kakaoAccessClientLoginService.getKakaoUserId(tokenRequest)
        return memberBusinessService.getByOauthId(savedId.findMemberId())
            ?: signUpNewMember(tokenRequest, oauthType) ?:
    }

    fun signUpNewMember(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member? {
        val savedInfo = kakaoAccessClientLoginService.getKakaoSelectedUserInfo(tokenRequest)
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
