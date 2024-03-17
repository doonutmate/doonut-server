package com.doonutmate.member.controller.dto

import com.doonutmate.doonut.member.model.OauthType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "회원탈퇴 요청")
data class MemberDeleteRequest(

    @Schema(title = "소셜 로그인 유형", example = "KAKAO")
    val oauthType: OauthType,

    @Schema(title = "애플로 로그인한 회원일 때 회원 코드")
    val code: String?,

    @Schema(title = "회원 탈퇴 사유")
    val reason: String?,
)
