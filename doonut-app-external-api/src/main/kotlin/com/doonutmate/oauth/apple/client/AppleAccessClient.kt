package com.doonutmate.oauth.apple.client

import com.doonutmate.oauth.apple.constants.AppleOauthConstants
import com.doonutmate.oauth.apple.dto.ApplePublicKeyResponse
import com.doonutmate.oauth.apple.dto.AppleTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange(AppleOauthConstants.APPLE_URL)
interface AppleAccessClient {
    @GetExchange(AppleOauthConstants.GET_PUBLIC_KEYS)
    fun getApplePublicKeys(): ApplePublicKeyResponse

    @PostExchange(AppleOauthConstants.CREATE_TOKEN)
    fun createAppleToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("grant_type") grantType: String,
        @RequestParam("code") code: String,
    ): AppleTokenResponse

    @PostExchange(AppleOauthConstants.CREATE_TOKEN)
    fun reissuanceAccessTokens(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("grant_type") grantType: String,
        @RequestParam("refresh_token") refresh_token: String,
    ): AppleTokenResponse

    @PostExchange(AppleOauthConstants.REVOKE_TOKEN)
    fun revokeToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("token") token: String,
    ): ResponseEntity<Unit>
}
