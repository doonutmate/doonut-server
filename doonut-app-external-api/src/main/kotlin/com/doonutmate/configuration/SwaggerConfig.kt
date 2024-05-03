package com.doonutmate.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
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
