package com.doonutmate.example.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExampleConfig {
    @Bean
    fun getExampleApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("example")
            .pathsToMatch("/example/**")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.example.controller")
            .build()
    }
}
