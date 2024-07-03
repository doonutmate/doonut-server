package com.doonutmate.fcm.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class FcmAccessClientConfig {

    @Value("\${fcm.url}")
    private lateinit var fcmUrl: String

    @Bean
    internal fun fcmOpenApiClient(): FcmAccessClient {
        val webClient = WebClient.builder().baseUrl(fcmUrl).build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return factory.createClient(FcmAccessClient::class.java)
    }
}
