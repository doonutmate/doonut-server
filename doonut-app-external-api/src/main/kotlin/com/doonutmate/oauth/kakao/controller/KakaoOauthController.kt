package com.doonutmate.oauth.kakao.controller

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.service.KakaoOauthLoginService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "kakao", description = "카카오 Oauth")
@RequestMapping("/kakao")
class KakaoOauthController(
    private val kakaoOauthLoginService: KakaoOauthLoginService,
) {
    @Operation(summary = "카카오 토큰, Oauth타입에 따른 카카오 회원가입", description = "중복 검사를 통해, 이미 있는 회원이면 기존 값 조회, 새로운 회원이면 회원가입")
    @PostMapping("login/{oauthType}")
    fun login(@PathVariable oauthType: OauthType, @RequestBody tokenRequest: KakaoTokenRequest): Member? {
        return kakaoOauthLoginService.Login(tokenRequest, oauthType)
    }
}
