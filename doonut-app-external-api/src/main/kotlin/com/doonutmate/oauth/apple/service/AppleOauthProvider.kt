package com.doonutmate.oauth.apple.service

import com.doonutmate.doonut.member.model.Member
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.doonut.member.service.MemberBusinessService
import com.doonutmate.name.RandomNameGenerator
import com.doonutmate.oauth.apple.client.AppleAccessClient
import com.doonutmate.oauth.apple.dto.AppleIdResponse
import com.doonutmate.oauth.apple.dto.AppleOauthRequest
import com.doonutmate.oauth.apple.dto.AppleTokenResponse
import com.doonutmate.oauth.apple.utils.AppleClaimsValidator
import com.doonutmate.oauth.apple.utils.AppleJwtParser
import com.doonutmate.oauth.apple.utils.ApplePrivateKeyGenerator
import com.doonutmate.oauth.apple.utils.ApplePublicKeyGenerator
import com.doonutmate.oauth.controller.dto.LoginRequest
import com.doonutmate.oauth.service.OauthProvider
import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.PublicKey
import java.time.Instant

@Service
class AppleOauthProvider(
    private val appleJwtParser: AppleJwtParser,
    private val appleClient: AppleAccessClient,
    private val applePublicKeyGenerator: ApplePublicKeyGenerator,
    private val applePrivateKeyGenerator: ApplePrivateKeyGenerator,
    private val appleClaimsValidator: AppleClaimsValidator,
    private val memberBusinessService: MemberBusinessService,

    @Value("\${apple.oauth.kid}") private val KID: String,
    @Value("\${apple.oauth.sub}") private val SUB: String,
    @Value("\${apple.oauth.team-id}") private val TEAD_ID: String,
) : OauthProvider<AppleIdResponse, AppleOauthRequest> {

    override fun getUserId(loginRequest: LoginRequest): AppleIdResponse {
        val claims = validateAndCreateClaims(loginRequest)
        validateClaims(claims)
        return AppleIdResponse(
            claims.subject,
        )
    }

    override fun getUserInfo(loginRequest: LoginRequest): AppleOauthRequest {
        val claims = validateAndCreateClaims(loginRequest)
        validateClaims(claims)
        return AppleOauthRequest(
            claims.subject,
            claims["name", String::class.java],
            claims["email", String::class.java],
        )
    }

    override fun signUp(loginRequest: LoginRequest): Long {
        val req: AppleOauthRequest = getUserInfo(loginRequest)
        val newMember = Member.builder()
            .name(getRandomName(req.name))
            .email(req.email)
            .oauthId(req.oauthId)
            .oauthType(OauthType.APPLE)
            .serviceAlarm(false)
            .lateNightAlarm(false)
            .marketingReceiveConsent(false)
            .marketingReceiveConsentUpdatedAt(Instant.now())
            .deleted(false)
            .build()

        return memberBusinessService.create(newMember)
    }

    private fun getRandomName(name: String?): String {
        if (!name.isNullOrBlank()) {
            return name
        }
        return RandomNameGenerator.generateRandomName()
    }

    fun createAuthToken(code: String): AppleTokenResponse {
        val appleSecret = applePrivateKeyGenerator.createClientSecret(KID, SUB, TEAD_ID)
        return appleClient.createAppleToken(SUB, appleSecret, "authorization_code", code)
    }

    fun reissueAccessTokens(refreshToken: String): AppleTokenResponse {
        val appleSecret = applePrivateKeyGenerator.createClientSecret(KID, SUB, TEAD_ID)
        return appleClient.reissuanceAccessTokens(SUB, appleSecret, "refresh_token", refreshToken)
    }

    fun revokeAccessToken(accessToken: String) {
        val appleSecret = applePrivateKeyGenerator.createClientSecret(KID, SUB, TEAD_ID)
        appleClient.revokeToken(SUB, appleSecret, accessToken, "refresh_token")
    }
    // TODO 토큰 revoke 실패시 처리

    private fun validateAndCreateClaims(loginRequest: LoginRequest): Claims {
        val headers = appleJwtParser.parseHeaders(loginRequest.accessToken)

        val applePublicKeyResponse = appleClient.getApplePublicKeys()

        val publicKey: PublicKey = applePublicKeyGenerator.generatePublicKey(headers, applePublicKeyResponse)

        return appleJwtParser.parsePublicKeyAndGetClaims(loginRequest.accessToken, publicKey)
    }

    private fun validateClaims(claims: Claims) {
        if (!appleClaimsValidator.isValid(claims)) {
            throw RuntimeException()
        }
    }
}
