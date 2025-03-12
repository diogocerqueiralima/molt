package com.github.molt.authorizationserver.config

import com.github.molt.authorizationserver.domain.User
import com.github.molt.authorizationserver.services.OidcUserInfoService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer

@Configuration
class ClaimConfig(

    private val oidcUserInfoService: OidcUserInfoService

) {

    @Bean
    fun jwtTokenCustomizer() = OAuth2TokenCustomizer<JwtEncodingContext> { ctx ->

        val authentication = ctx.getPrincipal<Authentication>()
        val user = authentication.principal as User

        ctx.claims.claims {
            it["sub"] = user.id
        }

        if (ctx.tokenType.value === OidcParameterNames.ID_TOKEN) {

            val oidcUserInfo = oidcUserInfoService.loadOidcUserInfo(user.username)
            val grantedScopes = ctx.authorization?.authorizedScopes ?: emptySet()

            ctx.claims.claims {

                if (OidcScopes.EMAIL in grantedScopes)
                    it["email"] = oidcUserInfo.email

                if (OidcScopes.PROFILE in grantedScopes) {
                    it["given_name"] = oidcUserInfo.givenName
                    it["family_name"] = oidcUserInfo.familyName
                    it["name"] = oidcUserInfo.fullName
                    it["preferred_username"] = oidcUserInfo.preferredUsername
                }

            }

        }

    }

}