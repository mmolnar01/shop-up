package com.mmolnar.ShopUp2.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests({ authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/**").hasAnyAuthority(ADMIN, USER)
                    .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyAuthority(ADMIN, USER)
                    .requestMatchers("/api/products", "/api/products/**").hasAuthority(ADMIN)
                    .requestMatchers("/api/users", "/api/users/**").hasAuthority(ADMIN)
                    .requestMatchers("/public/**", "/auth/**").permitAll()
                    .requestMatchers(
                        "/",
                        "/error",
                        "/csrf",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs",
                        "/v3/api-docs/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            })
            .httpBasic(Customizer.withDefaults())
            .sessionManagement({ sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) })
            .csrf({ obj: AbstractHttpConfigurer<*, *>? -> obj!!.disable() })
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    companion object {
        val ADMIN = "ADMIN"
        val USER = "USER"
    }

}