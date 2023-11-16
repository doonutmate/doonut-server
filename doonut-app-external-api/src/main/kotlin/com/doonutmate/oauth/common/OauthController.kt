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
    @Operation(summary = "Oauth 타입 별 로그인", description = "각 Oauth 타입에 맞는 로그인을 진행한 뒤 AccessToken을 반환한다.")
    @PostMapping("/login")
    fun login(@RequestParam oauthType: OauthType, @RequestBody loginRequest: LoginRequest): LoginResponse {
        return oauthService.login(loginRequest, oauthType)
    }
}
