package com.github.molt.productsservice.exceptions

class ProductNotFoundException(

    override val message: String = "Product not found"

) : RuntimeException(message)