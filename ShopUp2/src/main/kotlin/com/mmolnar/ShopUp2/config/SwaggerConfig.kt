package com.mmolnar.ShopUp2.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Value("\${spring.application.name}")
    private lateinit var applicationName: String

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(BASIC_AUTH_SECURITY_SCHEME,
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
            .info(Info().title(applicationName))
    }

    companion object {
        const val BASIC_AUTH_SECURITY_SCHEME = "basicAuth"
    }
}