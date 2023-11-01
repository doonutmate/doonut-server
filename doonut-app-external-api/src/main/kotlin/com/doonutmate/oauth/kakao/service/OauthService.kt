package com.doonutmate.oauth.kakao.service

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OauthService() {
    fun extractKakaoUserInfo(kakaoUserInfo: ResponseEntity<String>, vararg keyNames: String): Map<String, String> {
        val jsonParser = JSONParser()
        val jsonObj = jsonParser.parse(kakaoUserInfo.body) as JSONObject
        return keyNames.associateWithTo(mutableMapOf()) { jsonObj[it]?.toString() ?: "" }
    }
}
