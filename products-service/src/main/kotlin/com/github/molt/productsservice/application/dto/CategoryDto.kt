package com.github.molt.productsservice.application.dto

import com.github.molt.productsservice.domain.model.Category

data class CategoryDto(

    val id: Long,
    val name: String,
    val description: String

)

fun Category.toDto() = CategoryDto(
    id = this.id,
    name = this.name,
    description = this.description
)