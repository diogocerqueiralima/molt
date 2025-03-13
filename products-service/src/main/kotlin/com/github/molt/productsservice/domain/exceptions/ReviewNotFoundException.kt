package com.github.molt.productsservice.domain.exceptions

class ReviewNotFoundException(

    override val message: String = "Review not found"

) : RuntimeException(message)