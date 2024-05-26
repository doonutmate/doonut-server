package com.doonutmate.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerConfig(
    converter: MappingJackson2HttpMessageConverter,
) : WebMvcConfigurer {

    init {
        val supportedMediaTypes = ArrayList(converter.supportedMediaTypes)
        supportedMediaTypes.add(MediaType("application", "octet-stream"))
        converter.supportedMediaTypes = supportedMediaTypes
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "bearerAuth",
                    SecurityScheme().apply {
                        name = "bearerAuth"
                        type = SecurityScheme.Type.HTTP
                        scheme = "bearer"
                        bearerFormat = "JWT"
                    },
                ),
            )
            .info(
                Info().title("doonut-app-exteranl-api")
                    .description("")
                    .version("1.0"),
            )
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
    }
}
