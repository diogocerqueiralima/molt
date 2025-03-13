package com.github.molt.productsservice.application.dto

import com.github.molt.productsservice.domain.model.Review

data class ReviewDto(

    val id: Long,

    val comment: String,

    val rating: Int

)

fun Review.toDto() = ReviewDto(
    id = this.id,
    comment = this.comment,
    rating = this.rating
)