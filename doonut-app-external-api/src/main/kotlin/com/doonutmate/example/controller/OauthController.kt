package com.doonutmate.example.controller

import com.doonutmate.example.controller.dto.TokenRequest
import com.doonutmate.example.service.OauthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "kakao", description = "카카오 Oauth")
@RequestMapping("/oauth2")
class OauthController(
    private val oauthService: OauthService,
) {
    @Operation(summary = "", description = "example을 생성하는 API입니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공", content = [(Content())]),
        ],
    )
    @PostMapping("/access/token")
    fun accessToken(@RequestBody tokenRequest: TokenRequest): Long {
        return oauthService.getUserId(tokenRequest.accessToken)
    }
}
