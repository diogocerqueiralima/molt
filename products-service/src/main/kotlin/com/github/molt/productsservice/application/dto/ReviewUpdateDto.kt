package com.github.molt.productsservice.application.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class ReviewUpdateDto(

    @field:Min(0)
    @field:Max(10)
    val rating: Int?,

    val comment: String?

)
