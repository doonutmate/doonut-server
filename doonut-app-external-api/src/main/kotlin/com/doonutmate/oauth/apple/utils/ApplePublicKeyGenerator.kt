package com.doonutmate.oauth.apple.utils

import com.doonutmate.oauth.apple.dto.ApplePublicKey
import com.doonutmate.oauth.apple.dto.ApplePublicKeyResponse
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.RSAPublicKeySpec
import java.util.Base64

@Component
class ApplePublicKeyGenerator {
    fun generatePublicKey(
        tokenHeaders: Map<String, String>,
        applePublicKeys: ApplePublicKeyResponse,
    ): PublicKey {
        val publicKey: ApplePublicKey = applePublicKeys.getMatchedKey(
            tokenHeaders["kid"] ?: "unknown",
            tokenHeaders["alg"] ?: "unknown",
        )
        return getPublicKey(publicKey)
    }

    private fun getPublicKey(publicKey: ApplePublicKey): PublicKey {
        val nBytes: ByteArray = Base64.getUrlDecoder().decode(publicKey.n)
        val eBytes: ByteArray = Base64.getUrlDecoder().decode(publicKey.e)
        val publicKeySpec = RSAPublicKeySpec(
            BigInteger(1, nBytes),
            BigInteger(1, eBytes),
        )
        val keyFactory = KeyFactory.getInstance(publicKey.kty)
        return keyFactory.generatePublic(publicKeySpec)
    }
}
