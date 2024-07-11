package com.doonutmate.fcm.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FcmConfig {
    @Bean
    fun getFcmApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("Fcm 알람")
            .pathsToMatch("/fcm/*")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.fcm.controller")
            .build()
    }
}
