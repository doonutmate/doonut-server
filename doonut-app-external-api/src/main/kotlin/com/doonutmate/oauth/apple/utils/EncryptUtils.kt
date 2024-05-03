package com.doonutmate.oauth.apple.utils

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object EncryptUtils {
    fun encrypt(value: String): String {
        return try {
            val sha256 = MessageDigest.getInstance("SHA-256")
            val digest = sha256.digest(value.toByteArray(StandardCharsets.UTF_8))
            val hexString = StringBuilder()
            for (b in digest) {
                hexString.append(String.format("%02x", b))
            }
            hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalArgumentException("Apple OAuth 통신 암호화 과정 중 문제가 발생했습니다.")
        }
    }
}