package com.doonutmate.member.controller

import com.doonutmate.member.controller.dto.DeleteRequest
import com.doonutmate.member.controller.dto.MemberDeleteRequest
import com.doonutmate.member.controller.dto.MyPageResponse
import com.doonutmate.member.service.MemberAppService
import com.doonutmate.member.service.MemberProfileFacadeService
import com.doonutmate.oauth.configuration.Authorization
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@Tag(name = "Member", description = "멤버 API")
@RequestMapping("/member")
class MemberController(
    @Autowired var memberAppService: MemberAppService,
    private val memberProfileFacadeService: MemberProfileFacadeService,
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
        @RequestBody req: MemberDeleteRequest,
    ): ResponseEntity<Void> {
        memberAppService.delete(DeleteRequest(memberId, req.code, req.oauthType), req.reason)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "프로필 수정", description = "멤버가 프로필을 수정한다.")
    @PutMapping(
        value = ["/profile"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun updateProfile(
        @Authorization
        @Parameter(hidden = true)
        memberId: Long,
        @RequestPart("multipartFile") multipartFile: MultipartFile,
        @RequestParam("name") name: String,
    ): Long {
        return memberProfileFacadeService.updateMyPage(memberId, name, multipartFile)
    }
}
