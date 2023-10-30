package com.doonutmate.example.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class OauthService {

    fun getUserId(accessToken: String): ResponseEntity<String> {
        val kakaoUserInfoEndPoint: String = "https://kapi.kakao.com/v1/user/update_profile"

        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $accessToken")
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8")

        val body = LinkedMultiValueMap<String, String>()
//            body.add("CUSTOM_PROPERTY_KEY", "CUSTOM_PROPERTY_VALUE")

        val restTemplate = RestTemplate()
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(headers)

        val response: ResponseEntity<String> = restTemplate.exchange(
            kakaoUserInfoEndPoint,
            HttpMethod.POST,
            httpEntity,
            String::class.java,
        )

        return response
    }
}
