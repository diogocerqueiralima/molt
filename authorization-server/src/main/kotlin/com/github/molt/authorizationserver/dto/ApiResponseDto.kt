package com.github.molt.authorizationserver.dto

import kotlinx.serialization.Serializable

@Serializable
open class ApiResponseDto<T>(

    val message: String,
    open val data: T? = null

)
