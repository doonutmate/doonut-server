package com.doonutmate.calendar.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CalendarConfig {
    @Bean
    fun getCalendarApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("캘린더")
            .pathsToMatch("/calendar/**")
            .pathsToExclude("")
            .packagesToScan("com.doonutmate.calendar.controller")
            .build()
    }
}
