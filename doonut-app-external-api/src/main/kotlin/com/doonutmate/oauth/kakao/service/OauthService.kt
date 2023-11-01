package com.doonutmate.oauth.kakao.service

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OauthService() {

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
