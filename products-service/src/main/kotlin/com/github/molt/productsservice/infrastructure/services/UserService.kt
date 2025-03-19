package com.github.molt.productsservice.infrastructure.services

import com.github.molt.productsservice.application.dto.UserApiResponseDto
import com.github.molt.productsservice.application.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class UserService(

    private val restTemplate: RestTemplate

    ) {

    private val userServiceUri = "http://users-service:8080/api/v1/users"

    fun getFullName(userId: Long): UserDto {

        val responseEntity = restTemplate.getForEntity("$userServiceUri/${userId}", UserApiResponseDto::class.java)
        val apiResponse = responseEntity.body ?: throw RuntimeException("Body is missing")

        return apiResponse.data
    }

}