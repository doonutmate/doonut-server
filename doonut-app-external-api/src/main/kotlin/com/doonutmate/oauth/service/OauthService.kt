package com.doonutmate.oauth.service

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Service
class OauthService() {
    @Value("\${END_POINT.KAKAO_USER_INFO}")
    private lateinit var kakaoUserInfoEndPoint: String

//    fun getKakaoUserInfo(accessToken: String): ResponseEntity<String> {
//        try {
//            val headers = HttpHeaders()
//            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
//            headers.add("Authorization", "Bearer $accessToken")
//
//            val httpEntity = HttpEntity<String>(headers)
//
//            return restTemplate.exchange(
//                kakaoUserInfoEndPoint,
//                HttpMethod.POST,
//                httpEntity,
//                String::class.java,
//            )
//        } catch (e: RestClientException) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body("InvalidTokenException")
//        }
//    }


    fun extractKakaoUserInfo(kakaoUserInfo: ResponseEntity<String>, vararg keyNames: String): Map<String, String> {
        val jsonParser = JSONParser()
        val jsonObj = jsonParser.parse(kakaoUserInfo.body) as JSONObject
        return keyNames.associateWithTo(mutableMapOf()) { jsonObj[it]?.toString() ?: "" }
    }
}
