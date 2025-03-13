package com.github.molt.productsservice.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDto<T>(

    val message: String,
    val data: T? = null

)