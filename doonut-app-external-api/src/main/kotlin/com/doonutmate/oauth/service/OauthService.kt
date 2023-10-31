package com.doonutmate.oauth.service

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OauthService() {
    companion object {
        const val kakaoUserInfoEndPoint = "https://kapi.kakao.com/v2/user/me"
    }
    fun getKakaoUserId(accessToken: String): Long {
        val headers = HttpHeaders()

        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
        headers.add("Authorization", "Bearer $accessToken")

        val restTemplate = RestTemplate()
        val httpEntity = HttpEntity<String>(headers)

        val response: ResponseEntity<String> = restTemplate.exchange(
            kakaoUserInfoEndPoint,
            HttpMethod.POST,
            httpEntity,
            String::class.java,
        )

        val jsonParser = JSONParser()
        val jsonObj = jsonParser.parse(response.body) as JSONObject
        return jsonObj["id"] as Long
    }
}
