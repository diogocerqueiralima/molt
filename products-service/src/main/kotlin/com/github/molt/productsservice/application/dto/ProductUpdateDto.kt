package com.github.molt.productsservice.application.dto

data class ProductUpdateDto(

    val name: String? = null,

    val description: String? = null,

    val price: Double? = null,

    val categories: List<Long>? = null

)