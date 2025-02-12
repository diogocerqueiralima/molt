package com.github.diogocerqueiralima.productsservice.exceptions

class ProductNotFoundException(

    override val message: String = "Product not found"

) : RuntimeException(message)