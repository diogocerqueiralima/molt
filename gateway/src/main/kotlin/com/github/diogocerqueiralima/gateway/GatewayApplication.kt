package com.github.diogocerqueiralima.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GatewayApplication {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("products-service") { r ->
                r
                    .path("/api/v1/categories/**", "/api/v1/products/**", "/api/v1/reviews/**")
                    .filters { it.tokenRelay() }
                    .uri("http://products-service:8080")
            }
            .build()
    }

}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}
