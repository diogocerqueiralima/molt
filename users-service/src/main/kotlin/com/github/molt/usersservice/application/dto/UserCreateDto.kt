package com.github.molt.usersservice.application.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCreateDto(

    @field:NotBlank
    val username: String,

    @field:Email
    @field:NotNull
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    @SerialName("first_name")
    val firstName: String,

    @field:NotBlank
    @SerialName("last_name")
    val lastName: String

)