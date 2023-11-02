package com.doonutmate.oauth.kakao.controller

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import com.doonutmate.oauth.kakao.service.KakaoAccessClientLoginService
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
    private val kakaoAccessTokenLoginClient: KakaoAccessClientLoginService,
    private val kakaoOauthLoginService: KakaoOauthLoginService,
) {
    @Operation(summary = "kakaoUserId 리턴", description = "accessToken 을 이용해서 카카오 사용자의 Json id를 받아옴")
    @PostMapping("/access/id")
    fun getKakaoUserId(@RequestBody tokenRequest: KakaoTokenRequest): TokenIdResponse {
        return kakaoAccessTokenLoginClient.getKakaoUserId(tokenRequest)
    }

//    @PostMapping("/access/userinfo")
//    fun getKakaoUserInfo(@RequestBody tokenRequest: KakaoTokenRequest): ResponseEntity<KakaoInfoResponse> {
//        return kakaoAccessTokenLoginClient.getKakaoUserInfo(tokenRequest)
//    }

    @PostMapping("/access/selected/userinfo")
    fun getKakaoSelctedUserInfo(@RequestBody tokenRequest: KakaoTokenRequest): KakaoInfoResponse {
        return kakaoAccessTokenLoginClient.getKakaoSelectedUserInfo(tokenRequest)
    }

    @PostMapping("login/{oauthType}")
    fun signUp(@PathVariable oauthType: OauthType, @RequestBody tokenRequest: KakaoTokenRequest): Member? {
        return kakaoOauthLoginService.kakaoLoginPreventDuplication(tokenRequest, oauthType)
    }
}
