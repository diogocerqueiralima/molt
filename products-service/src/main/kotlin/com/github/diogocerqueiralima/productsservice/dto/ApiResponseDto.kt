package com.github.diogocerqueiralima.productsservice.dto

data class ApiResponseDto<T>(

    val message: String,
    val data: T? = null

)