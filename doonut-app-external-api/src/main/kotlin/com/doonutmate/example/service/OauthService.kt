package com.doonutmate.example.service

import com.google.gson.JsonObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class OauthService {

    fun getUserId(accessToken: String): ResponseEntity<String> {
        val kakaoUserInfoEndPoint = UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me")
        val headers = HttpHeaders()

        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
        headers.add("Authorization", "Bearer $accessToken")

        val restTemplate = RestTemplate()
        val httpEntity = HttpEntity<String>(headers)

        val response: ResponseEntity<String> = restTemplate.exchange(
            kakaoUserInfoEndPoint.toUriString(),
            HttpMethod.POST,
            httpEntity,
            String::class.java,
        )

        return response
    }
}
