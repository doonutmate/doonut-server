package com.doonutmate.oauth.kakao.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KakaoConfig {
    @Bean
    fun getKakaoOauthApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("kakaoOauth")
            .pathsToMatch("/oauth/**")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.oauth.kakao.controller")
            .build()
    }
}
