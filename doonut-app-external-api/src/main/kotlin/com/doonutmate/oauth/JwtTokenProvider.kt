package com.doonutmate.oauth

import com.doonutmate.oauth.exception.InvalidTokenException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey


@Component
class JwtTokenProvider(
    @Value("\${security.jwt.token.secret-key}") secretKey: String,
    @Value("\${security.jwt.token.expire-length}") private val validityInMilliseconds: Long
) {
    private val key: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    fun createToken(payload: String?): String {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        return Jwts.builder()
            .setSubject(payload)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getPayload(token: String?): String {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: JwtException) {
            throw InvalidTokenException()
        }
    }
}
