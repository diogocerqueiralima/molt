package com.github.molt.productsservice.domain.exceptions

class ReviewOwnerException(

    override val message: String = "The review was not created by the user"

) : RuntimeException(message)