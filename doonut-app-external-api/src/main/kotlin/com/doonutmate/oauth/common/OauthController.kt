package com.doonutmate.oauth.common

import com.doonutmate.doonut.member.model.OauthType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "oauth", description = "Oauth 컨트롤러")
@RequestMapping("/oauth")
class OauthController(
    private val oauthService: OauthService,
) {
    @Operation(summary = "Oauth타입에 따른 회원가입", description = "중복 검사를 통해, 이미 있는 회원이면 기존 값 조회, 새로운 회원이면 회원가입")
    @PostMapping("/login")
    fun login(@RequestParam oauthType: OauthType, @RequestBody tokenRequest: TokenRequest): String {
        return oauthService.login(tokenRequest, oauthType)
    }
}
