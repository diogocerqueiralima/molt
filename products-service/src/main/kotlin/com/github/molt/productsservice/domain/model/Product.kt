package com.github.molt.productsservice.domain.model

import org.springframework.data.domain.Sort
import java.time.LocalDateTime

data class Product(

    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val releaseDate: LocalDateTime = LocalDateTime.now(),
    val categories: List<Category> = emptyList(),
    val reviews: List<Review> = emptyList()

) {

    init {
        require(id > 0) { "Product id must be greater than zero" }
        require(name.isNotBlank()) { "Name must not be blank" }
        require(description.isNotBlank()) { "Description must not be blank" }
        require(price >= 0 && price.isFinite()) { "Price must be valid" }
    }

    fun averageRating() = this.reviews
        .map { it.rating }
        .average()
        .toInt()

    enum class Order(
        val field: String,
        val direction: Sort.Direction
    ) {

        MOST_RECENT("releaseDate", Sort.Direction.DESC),
        OLDEST("releaseDate", Sort.Direction.ASC),
        LOWEST_PRICE("price", Sort.Direction.ASC),
        HIGHEST_PRICE("price", Sort.Direction.DESC)

    }

}
