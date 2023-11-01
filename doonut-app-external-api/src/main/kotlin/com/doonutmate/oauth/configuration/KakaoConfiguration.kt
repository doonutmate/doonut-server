package com.doonutmate.oauth.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class KakaoConfiguration {

    @Bean
    internal fun kakaoOpenApiClient(): KakaoOpenApiClient {
        val webClient = WebClient.builder().build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return factory.createClient(KakaoOpenApiClient::class.java)
    }

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
