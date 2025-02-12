package com.github.diogocerqueiralima.productsservice.dto

data class ProductDto(

    val id: Long,

    val name: String,

    val description: String,

    val price: Double,

    val categories: List<Long>

)
