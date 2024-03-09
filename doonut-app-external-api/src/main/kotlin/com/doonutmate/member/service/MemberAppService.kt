package com.doonutmate.member.service

import com.doonutmate.doonut.member.service.MemberBusinessService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberAppService(
    @Autowired var memberBusinessService: MemberBusinessService,
) {
    fun delete(memberId: Long) {
        memberBusinessService.delete(memberId)

        // TODO 애플 소셜 로그인 회원 시 APPLE 서버로 회원 탈퇴 API를 날리도록 로직 추가
    }
}
