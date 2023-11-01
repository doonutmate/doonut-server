package com.doonutmate.oauth.kakao.controller

import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.KakaoTokenRequest
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import com.doonutmate.oauth.kakao.service.KakaoAccessClientLoginService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "kakao", description = "카카오 Oauth")
@RequestMapping("/kakao")
class OauthController(
    private val kakaoAccessTokenLoginClient: KakaoAccessClientLoginService,
) {
    @Operation(summary = "kakaoUserId 리턴", description = "accessToken 을 이용해서 카카오 사용자의 Json id를 받아옴")
    @PostMapping("/access/token")
    fun getId(@RequestBody tokenRequest: KakaoTokenRequest): TokenIdResponse {
        return kakaoAccessTokenLoginClient.getKakaoUserId(tokenRequest)
    }

    @PostMapping("/access/token2")
    fun getInfo(@RequestBody tokenRequest: KakaoTokenRequest): ResponseEntity<KakaoInfoResponse> {
        return kakaoAccessTokenLoginClient.getKakaoUserInfo(tokenRequest)
    }
}
