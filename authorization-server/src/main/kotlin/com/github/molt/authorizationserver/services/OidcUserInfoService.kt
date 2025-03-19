package com.github.molt.authorizationserver.services

import com.github.molt.authorizationserver.dto.UserApiResponseDto
import com.github.molt.authorizationserver.exceptions.UserNotFoundException
import org.springframework.security.oauth2.core.oidc.OidcUserInfo
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OidcUserInfoService(

    private val restTemplate: RestTemplate

    ) {

    private val userServiceUri = "http://users-service:8080/api/v1/users"

    fun loadOidcUserInfo(username: String): OidcUserInfo {

        val responseEntity = restTemplate.getForEntity("$userServiceUri?username=$username", UserApiResponseDto::class.java)
        val apiResponse = responseEntity.body ?: throw RuntimeException("Body is missing")
        val user = apiResponse.data

        return OidcUserInfo.builder()
            .subject(user.id.toString())
            .name(user.fullName)
            .givenName(user.firstName)
            .familyName(user.lastName)
            .preferredUsername(user.username)
            .email(user.email)
            .build()
    }

}