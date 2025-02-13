package com.github.diogocerqueiralima.productsservice.dto

import jakarta.validation.constraints.NotNull

data class ProductUpdateDto(

    @field:NotNull
    val id: Long,

    val name: String? = null,

    val description: String? = null,

    val price: Double? = null,

    val categories: List<Long>? = null

)