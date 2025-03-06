package com.github.diogocerqueiralima.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun defaultSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .cors { cors ->
                cors.configurationSource {
                    val corsConfiguration = CorsConfiguration()
                    corsConfiguration.allowedOrigins = listOf("http://localhost:3000")
                    corsConfiguration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
                    corsConfiguration.allowedHeaders = listOf("*")
                    corsConfiguration.allowCredentials = true
                    corsConfiguration
                }
            }
            .csrf { it.disable() }
            .oauth2Login { oauth2 ->
                oauth2
                    .authenticationSuccessHandler(RedirectServerAuthenticationSuccessHandler("http://localhost:3000"))
            }
            .build()

}