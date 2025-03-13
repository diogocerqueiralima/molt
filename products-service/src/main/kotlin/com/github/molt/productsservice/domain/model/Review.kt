package com.github.molt.productsservice.domain.model

data class Review(

    val id: Long,
    val comment: String,
    val rating: Int,
    val userId: Long,
    val userFullName: String

) {

    init {
        require(id > 0) { "Review id must be greater than zero" }
        require(comment.isNotBlank()) { "Review comment must not be blank" }
        require(rating in 0..10) { "Review rating must be between 0 and 10" }
        require(userId > 0) { "Review user id must be greater than zero" }
        require(userFullName.isNotBlank()) { "Review user full name must not be blank" }
    }

}
