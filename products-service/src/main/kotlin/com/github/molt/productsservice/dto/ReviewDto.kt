package com.github.molt.productsservice.dto

import com.github.molt.productsservice.domain.Review
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(

    val id: Long,

    @SerialName("product_id")
    val productId: Long,

    val comment: String,

    val score: Int

)

fun Review.toDto() = ReviewDto(
    id = this.id,
    productId = this.product.id,
    comment = this.comment,
    score = this.score
)