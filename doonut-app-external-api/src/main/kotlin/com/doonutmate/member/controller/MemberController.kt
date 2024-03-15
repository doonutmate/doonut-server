package com.doonutmate.member.controller

import com.doonutmate.doonut.member.model.MemberDeleteOauthType
import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.service.MemberAppService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Member", description = "멤버 API")
@RequestMapping("/member")
class MemberController(
    @Autowired var memberAppService: MemberAppService,
) {
    @Operation(summary = "멤버 탈퇴", description = "멤버가 탈퇴한다.")
    @DeleteMapping
    fun delete(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
        @RequestParam memberDeleteOauthType: MemberDeleteOauthType,
        @RequestParam(required = false) code: String,
    ): ResponseEntity<Void> {
        memberAppService.delete(DeleteRequest(memberId, code, memberDeleteOauthType))
        return ResponseEntity.ok().build()
    }
}
