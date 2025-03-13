package com.github.molt.productsservice.application.dto

import jakarta.validation.constraints.NotNull

data class CategoryUpdateDto(

    @field:NotNull
    val id: Long,

    val name: String? = null,
    val description: String? = null,

)
