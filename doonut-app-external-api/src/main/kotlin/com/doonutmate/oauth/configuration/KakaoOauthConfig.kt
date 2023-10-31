package com.doonutmate.oauth.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KakaoOauthConfig {
    @Bean
    fun getKakaoOauthApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("kakaoOauth")
            .pathsToMatch("/oauth/**")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.oauth.controller")
            .build()
    }
}
