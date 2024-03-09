package com.doonutmate.oauth.apple.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.apple.client.AppleAccessClient
import com.doonutmate.oauth.apple.dto.AppleOauthRequest
import com.doonutmate.oauth.apple.utils.AppleClaimsValidator
import com.doonutmate.oauth.apple.utils.AppleJwtParser
import com.doonutmate.oauth.apple.utils.ApplePublicKeyGenerator
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service
import java.security.PublicKey

@Service
class AppleOauthProvider(
    private val appleJwtParser: AppleJwtParser,
    private val appleClient: AppleAccessClient,
    private val applePublicKeyGenerator: ApplePublicKeyGenerator,
    private val appleClaimsValidator: AppleClaimsValidator,
    private val memberBusinessService: MemberBusinessService,
) {

    fun signUp(identityToken: String): Long {
        val req: AppleOauthRequest = getApplePlatformMember(identityToken)
        val newMember = Member.builder()
            .name(req.name)
            .email(req.email)
            .oauthId(req.oauthId)
            .oauthType(OauthType.APPLE)
            .deleted(false)
            .build()

        return memberBusinessService.create(newMember)
    }

    fun getApplePlatformMember(identityToken: String): AppleOauthRequest {
        val headers = appleJwtParser.parseHeaders(identityToken)
        val applePublicKeyResponse = appleClient.getApplePublicKeys()

        val publicKey: PublicKey = applePublicKeyGenerator.generatePublicKey(headers, applePublicKeyResponse)

        val claims = appleJwtParser.parsePublicKeyAndGetClaims(identityToken, publicKey)
        validateClaims(claims)
        return AppleOauthRequest(
            claims.subject,
            claims["name", String::class.java],
            claims["email", String::class.java],
        )
    }

    private fun validateClaims(claims: Claims) {
        if (!appleClaimsValidator.isValid(claims)) {
            throw RuntimeException()
        }
    }
}
