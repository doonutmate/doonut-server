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

        // TODO 애플로 로그인한 멤버면 APPLE 서버로 탈퇴 API를 날리도록 로직 추가
    }
}
