package com.doonutmate.oauth.apple.dto

import com.doonutmate.oauth.exception.AppleKeyNotMatchedException

data class ApplePublicKeyResponse(
    val keys: List<ApplePublicKey>,
) {
    fun getMatchedKey(alg: String?, kid: String?): ApplePublicKey {
        return keys.firstOrNull { it.alg == alg && it.kid == kid }
            ?: throw AppleKeyNotMatchedException()
    }
}
