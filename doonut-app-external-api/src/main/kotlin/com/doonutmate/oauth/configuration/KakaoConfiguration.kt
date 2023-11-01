package com.doonutmate.oauth.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class KakaoConfiguration {

    @Value("\${END_POINT.KAKAO_OPEN_API_URL}")
    private lateinit var kakaoOpenApiUrl: String

    @Bean
    internal fun kakaoOpenApiClient(): KakaoOpenApiClient {
        val webClient = WebClient.builder().baseUrl(kakaoOpenApiUrl).build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()

        return factory.createClient(KakaoOpenApiClient::class.java)
    }
}
