package com.github.molt.authorizationserver.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterDto(

    @NotBlank
    val username: String = "",

    @Email
    val email: String = "",

    @Size(min = 8)
    val password: String = "",

    @SerialName("first_name")
    @NotBlank
    val firstName: String = "",

    @SerialName("last_name")
    @NotBlank
    val lastName: String = "",

    @Size(min = 8)
    @SerialName("confirm_password")
    val confirmPassword: String = ""

)
