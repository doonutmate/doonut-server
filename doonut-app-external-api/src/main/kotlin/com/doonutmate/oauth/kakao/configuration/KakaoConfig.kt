package com.doonutmate.oauth.kakao.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KakaoConfig {
    @Bean
    fun getKakaoOauthApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("Oauth")
            .pathsToMatch("/oauth/external")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.oauth.externalOauth")
            .build()
    }
}
