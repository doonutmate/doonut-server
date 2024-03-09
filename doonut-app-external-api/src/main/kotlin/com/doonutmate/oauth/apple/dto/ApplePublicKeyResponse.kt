package com.doonutmate.oauth.apple.dto

import com.doonutmate.oauth.exception.NotMatchedException

data class ApplePublicKeyResponse(
    val keys: List<ApplePublicKey>
) {
    fun getMatchedKey(alg: String, kid: String): ApplePublicKey =
        keys.firstOrNull { it.alg == alg && it.kid == kid }
            ?: throw NotMatchedException()
}
