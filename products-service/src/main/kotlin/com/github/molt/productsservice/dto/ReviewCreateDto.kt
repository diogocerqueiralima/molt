package com.github.molt.productsservice.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewCreateDto(

    @SerialName("product_id")
    @field:NotNull
    val productId: Long,

    @SerialName("user_id")
    @field:NotNull
    val userId: Long,

    @field:Min(0)
    @field:Max(10)
    @field:NotNull
    val score: Int,

    @field:NotBlank
    val comment: String

)
