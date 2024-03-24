package com.doonutmate.oauth.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OauthConfig {
    @Bean
    fun getKakaoOauthApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("Oauth")
            .pathsToMatch("/oauth/**")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.oauth.controller")
            .build()
    }
}
