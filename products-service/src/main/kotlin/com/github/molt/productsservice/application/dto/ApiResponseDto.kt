package com.github.molt.productsservice.application.dto

import kotlinx.serialization.Serializable

@Serializable
open class ApiResponseDto<T>(

    val message: String,
    open val data: T? = null

)

class UserApiResponseDto(

    override val data: UserDto,
    message: String

) : ApiResponseDto<UserDto>(message)