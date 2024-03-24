package com.doonutmate.member.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MemberConfig {
    @Bean
    fun getMemberApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("member")
            .pathsToMatch(
                "/members/**",
            )
            .pathsToExclude("")
            .packagesToScan(
                "com.doonutmate.member.controller",
            )
            .build()
    }
}
