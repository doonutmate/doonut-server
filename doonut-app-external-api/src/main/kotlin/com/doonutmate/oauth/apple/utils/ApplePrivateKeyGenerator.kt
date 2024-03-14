package com.doonutmate.oauth.apple.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.testcontainers.shaded.org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.testcontainers.shaded.org.bouncycastle.openssl.PEMParser
import org.testcontainers.shaded.org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import java.io.StringReader
import java.nio.file.Files
import java.nio.file.Paths
import java.security.PrivateKey
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Component
class ApplePrivateKeyGenerator {

    fun createClientSecret(kid: String, sub: String): String {
        val expirationDate = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant())
        return Jwts.builder()
            .setHeaderParam("kid", kid)
            .setHeaderParam("alg", "ES256")
            .setIssuer("DASKP64V6P")
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(expirationDate)
            .setAudience("https://appleid.apple.com")
            .setSubject(sub)
            .signWith(getPrivateKey(), SignatureAlgorithm.ES256)
            .compact()
    }

    private fun getPrivateKey(): PrivateKey {
        val resource = ClassPathResource("AuthKey_LMU9S63B75.p8")
        val privateKey = String(Files.readAllBytes(Paths.get(resource.uri)))
        val pemReader = StringReader(privateKey)
        PEMParser(pemReader).use { pemParser ->
            val converter = JcaPEMKeyConverter()
            val `object` = pemParser.readObject() as PrivateKeyInfo
            return converter.getPrivateKey(`object`)
        }
    }
}
