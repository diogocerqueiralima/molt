package com.github.molt.authorizationserver.services

import com.github.molt.authorizationserver.exceptions.UserNotFoundException
import org.springframework.security.oauth2.core.oidc.OidcUserInfo
import org.springframework.stereotype.Service

@Service
class OidcUserInfoService(

    //private val userRepository: UserRepository

) {

    /*fun loadOidcUserInfo(username: String): OidcUserInfo {

        val user = userRepository.findByUsernameOrEmail(username, username) ?: throw UserNotFoundException()

        return OidcUserInfo.builder()
            .subject(username)
            .name(user.name)
            .givenName(user.firstName)
            .familyName(user.lastName)
            .preferredUsername(user.username)
            .email(user.email)
            .build()
    }*/

}