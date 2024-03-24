package com.doonutmate.oauth.apple.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class AppleClientConfig {
    @Bean
    internal fun appleOpenApiClient(): AppleAccessClient {
        val webClient = WebClient.builder().build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return factory.createClient(AppleAccessClient::class.java)
    }
}
