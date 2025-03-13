package com.github.molt.productsservice.domain.model

data class Category(

    val id: Long,
    val name: String,
    val description: String

) {

    init {
        require(id > 0) { "Category ID must be greater than zero." }
        require(name.isNotBlank()) { "Category name must not be blank." }
        require(description.isNotBlank()) { "Category description must not be blank." }
    }

}
