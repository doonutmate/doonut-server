package com.doonutmate.oauth.controller

import com.doonutmate.oauth.configuration.KakaoAccessTokenLoginClient
import com.doonutmate.oauth.configuration.KakaoOpenApiClient
import com.doonutmate.oauth.dto.TokenIdResponse
import com.doonutmate.oauth.dto.TokenRequest
import com.doonutmate.oauth.service.OauthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@Tag(name = "kakao", description = "카카오 Oauth")
@RequestMapping("/kakao")
class OauthController(
    private val oauthService: OauthService,
    private val kakaoAccessTokenLoginClient: KakaoAccessTokenLoginClient,
) {
    @Operation(summary = "kakaoUserId 리턴", description = "accessToken 을 이용해서 카카오 사용자의 Json id를 받아옴")
    @PostMapping("/access/token")
    fun login(@RequestBody tokenRequest: TokenRequest): TokenIdResponse {
//        return oauthService.getKakaoUserInfo(tokenRequest.accessToken)
        return kakaoAccessTokenLoginClient.login(tokenRequest.accessToken)
    }
}
