package com.github.molt.authorizationserver.config

import com.github.molt.authorizationserver.domain.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer

@Configuration
class ClaimConfig {

    @Bean
    fun jwtTokenCustomizer() = OAuth2TokenCustomizer<JwtEncodingContext> { ctx ->

        if (ctx.tokenType === OAuth2TokenType.ACCESS_TOKEN) {

            val authentication = ctx.getPrincipal<Authentication>()
            val user = authentication.principal as User

            ctx.claims.claims {
                it["sub"] = user.id
            }
        }

    }

}