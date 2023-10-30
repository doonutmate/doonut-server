package com.doonutmate.example.controller

import com.doonutmate.example.controller.dto.TokenRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "kakao", description = "카카오 Oauth")
@RequestMapping("/oauth2")
class OauthController {
}
@Operation(summary = "", description = "example을 생성하는 API입니다.")
@PostMapping("/access/token")
fun accessToken(@RequestBody tokenRequest: TokenRequest):ResponseEntity<String>{



}