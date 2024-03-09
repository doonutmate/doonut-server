package com.doonutmate.oauth.apple.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.security.PublicKey
import java.util.Base64

@Component
class AppleJwtParser {
    fun parseHeaders(identityToken: String): Map<String, String> {
        try {
            val encodedHeader = identityToken.split(IDENTITY_TOKEN_VALUE_DELIMITER)[HEADER_INDEX]
            val decodedHeader = String(Base64.getDecoder().decode(encodedHeader))
            return OBJECT_MAPPER.readValue(decodedHeader)
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    fun parsePublicKeyAndGetClaims(idToken: String, publicKey: PublicKey): Claims {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(idToken)
                .body
        } catch (e: JwtException) {
            throw RuntimeException()
        }
    }

    companion object {
        private const val IDENTITY_TOKEN_VALUE_DELIMITER = "\\."
        private const val HEADER_INDEX = 0

        private val OBJECT_MAPPER = jacksonObjectMapper()
    }
}
