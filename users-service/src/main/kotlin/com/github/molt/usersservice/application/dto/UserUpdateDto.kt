package com.github.molt.usersservice.application.dto

import jakarta.validation.constraints.NotBlank

data class UserUpdateDto(

    @field:NotBlank
    val password: String

)
