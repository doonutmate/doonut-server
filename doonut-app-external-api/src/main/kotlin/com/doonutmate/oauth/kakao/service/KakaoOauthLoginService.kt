package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.repository.MemberRepository
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class KakaoOauthLoginService(
    private val kakaoAccessClientLoginService: KakaoAccessClientLoginService,
    private val memberBusinessService: MemberBusinessService,
    private val memberRepository: MemberRepository,
) {

    fun kakaoLoginPreventDuplication(tokenRequest: KakaoTokenRequest, oauthType: OauthType): Member? {
        val savedId = kakaoAccessClientLoginService.getKakaoUserId(tokenRequest)
        val existingMember = memberBusinessService.getByOauthId(savedId.findMemberId())

        existingMember ?: let {
            val a = signUpNewMember(tokenRequest, oauthType)
            return a
        }
        val member123: Member? = Member(1, "이거나오면 에러임", 2.toString(), 2.toString(), OauthType.KAKAO, false)
        return member123
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
