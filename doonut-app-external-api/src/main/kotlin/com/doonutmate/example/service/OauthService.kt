package com.doonutmate.example.service

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class OauthService {

    fun getUserId(accessToken: String): Long {
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

        val jsonParser = JSONParser()
        val jsonObj = jsonParser.parse(response.body) as JSONObject
        val userKakaoId = jsonObj.get("id") as Long

        return userKakaoId
    }
}
