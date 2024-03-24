package com.doonutmate.example.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ImageConfig {

    @Bean
    fun getImageApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("이미지 업로드")
            .pathsToMatch("/image*")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.image.controller")
            .build()
    }
}
