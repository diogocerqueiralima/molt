package com.github.diogocerqueiralima.productsservice.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProductCreateDto(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,

    @field:NotNull
    val price: Double,

    @field:NotNull
    val categories: List<Long>

)
