package com.doonutmate.oauth.apple.client

import com.doonutmate.oauth.apple.constants.AppleOauthConstants
import com.doonutmate.oauth.apple.dto.ApplePublicKeyResponse
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange(AppleOauthConstants.APPLE_URL)
interface AppleClient {
    @GetExchange(AppleOauthConstants.GET_PUBLIC_KEYS)
    fun getApplePublicKeys(): ApplePublicKeyResponse?
}