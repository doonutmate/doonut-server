package com.doonutmate.member.controller

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.controller.dto.MyPageResponse
import com.doonutmate.member.service.MemberAppService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Member", description = "멤버 API")
@RequestMapping("/member")
class MemberController(
    @Autowired var memberAppService: MemberAppService,
) {
    @Operation(summary = "마이페이지 조회", description = "마이페이지를 조회한다.")
    @GetMapping("/mypage")
    fun findMyInfo(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
    ): MyPageResponse {
        return memberAppService.findMyInfo(memberId)
    }

    @Operation(summary = "멤버 탈퇴", description = "멤버가 탈퇴한다.")
    @DeleteMapping
    fun delete(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
        @RequestParam oauthType: OauthType,
        @RequestParam(required = false) code: String,
    ): ResponseEntity<Void> {
        memberAppService.delete(DeleteRequest(memberId, code, oauthType))
        return ResponseEntity.ok().build()
    }
}
