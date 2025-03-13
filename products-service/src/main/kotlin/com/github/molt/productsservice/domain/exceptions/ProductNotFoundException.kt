package com.github.molt.productsservice.domain.exceptions

class ProductNotFoundException(

    override val message: String = "Product not found"

) : RuntimeException(message)