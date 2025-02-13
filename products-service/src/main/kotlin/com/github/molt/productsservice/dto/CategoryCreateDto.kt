package com.github.molt.productsservice.dto

import jakarta.validation.constraints.NotBlank

class CategoryCreateDto(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String

)