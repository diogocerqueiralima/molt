package com.github.diogocerqueiralima.productsservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/categories").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/*").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }
            .build()

}