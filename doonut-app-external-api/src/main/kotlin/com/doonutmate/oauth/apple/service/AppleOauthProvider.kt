package com.doonutmate.oauth.apple.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.oauth.apple.client.AppleAccessClient
import com.doonutmate.oauth.apple.dto.AppleOauthRequest
import com.doonutmate.oauth.apple.utils.AppleClaimsValidator
import com.doonutmate.oauth.apple.utils.AppleJwtParser
import com.doonutmate.oauth.apple.utils.ApplePublicKeyGenerator
import com.doonutmate.oauth.controller.dto.LoginRequest
import com.doonutmate.oauth.service.OauthProvider
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
) : OauthProvider<AppleOauthRequest, AppleOauthRequest> {

    override fun getUserId(loginRequest: LoginRequest): AppleOauthRequest {
        val headers = appleJwtParser.parseHeaders(loginRequest.accessToken)
        val applePublicKeyResponse = appleClient.getApplePublicKeys()

        val publicKey: PublicKey = applePublicKeyGenerator.generatePublicKey(headers, applePublicKeyResponse)

        val claims = appleJwtParser.parsePublicKeyAndGetClaims(loginRequest.accessToken, publicKey)
        validateClaims(claims)
        return AppleOauthRequest(
            claims.subject,
            claims["name", String::class.java],
            claims["email", String::class.java],
        )
    }

    override fun getUserInfo(loginRequest: LoginRequest): AppleOauthRequest {
        TODO("Not yet implemented")
    }

    override fun signUp(loginRequest: LoginRequest): Long {
        val req: AppleOauthRequest = getUserId(loginRequest)
        val newMember = Member.builder()
            .name(req.name)
            .email(req.email)
            .oauthId(req.id)
            .oauthType(OauthType.APPLE)
            .deleted(false)
            .build()

        return memberBusinessService.create(newMember)
    }

    private fun validateClaims(claims: Claims) {
        if (!appleClaimsValidator.isValid(claims)) {
            throw RuntimeException()
        }
    }
}
