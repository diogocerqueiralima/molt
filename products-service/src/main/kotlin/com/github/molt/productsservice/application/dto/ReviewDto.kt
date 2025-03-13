package com.github.molt.productsservice.application.dto

import com.github.molt.productsservice.domain.model.Review
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(

    val id: Long,

    val comment: String,

    val rating: Int,

    @SerialName("user_full_name")
    val userFullName: String,

)

fun Review.toDto() = ReviewDto(
    id = this.id,
    comment = this.comment,
    rating = this.rating,
    userFullName = this.userFullName
)