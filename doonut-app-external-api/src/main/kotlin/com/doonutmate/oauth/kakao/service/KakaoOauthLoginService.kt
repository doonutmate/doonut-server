package com.doonutmate.oauth.kakao.service

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.repository.MemberRepository
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.kakao.dto.LoginUserInfo
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.atomic.AtomicBoolean

@Service
@Transactional
class KakaoOauthLoginService(
    private val kakaoAccessClientLoginService: KakaoAccessClientLoginService,
    private val memberBusinessService: MemberBusinessService,
    private val memberRepository: MemberRepository,
) {
    fun kakaoLoginPreventDuplication(
        oauthType: OauthType,
        tokenIdResponse: TokenIdResponse,
    ): LoginUserInfo {
        val isSignUpUser = AtomicBoolean(false)
        val signInMember = memberRepository.findByOauthId(tokenIdResponse.findMemberId())
            .orElseGet {
                val savedMember =
                    memberBusinessService.getByOauthId(tokenIdResponse.findMemberId())

                isSignUpUser.set(true)
                val savedId = memberBusinessService.create(savedMember)
                memberRepository.getReferenceById(savedId)
            }

        return LoginUserInfo(signInMember, isSignUpUser.get())
    }
}
