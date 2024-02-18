package com.doonutmate.challenge.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChallengeConfig {
    @Bean
    fun getChallengeApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("챌린지")
            .pathsToMatch("/challenge/**")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.challenge.controller")
            .build()
    }
}
