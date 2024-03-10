package com.doonutmate.oauth.apple.utils

import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppleClaimsValidator(
    @Value("\${apple.iss}") private val iss: String,
    @Value("\${apple.client-id}") private val clientId: String,
    @Value("\${apple.nonce}") nonce: String,
) {
    private val nonce: String = EncryptUtils.encrypt(nonce)

    fun isValid(claims: Claims): Boolean =
        claims.issuer.contains(iss) &&
            claims.audience == clientId &&
            claims[NONCE_KEY, String::class.java] == this.nonce

    companion object {
        private const val NONCE_KEY = "nonce"
    }
}
