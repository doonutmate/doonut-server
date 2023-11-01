package com.doonutmate.oauth.kakao.client

import com.doonutmate.oauth.kakao.constants.Oauth2Constants
import com.doonutmate.oauth.kakao.dto.KakaoInfoResponse
import com.doonutmate.oauth.kakao.dto.TokenIdResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange(Oauth2Constants.KAKAO_URL)
interface KakaoAccessClient {
    @PostExchange(Oauth2Constants.KAKAO_USER_INFO)
    fun getKakaoUserId(
        @RequestHeader("Authorization") authorization: String,
    ): TokenIdResponse

    @PostExchange(Oauth2Constants.KAKAO_USER_INFO)
    fun getKakaoUserInfo(
        @RequestHeader("Authorization") authorization: String,
    ): ResponseEntity<KakaoInfoResponse>
}
