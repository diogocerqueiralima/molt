package com.github.molt.usersservice.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDto<T>(

    val message: String,
    val data: T? = null

)
