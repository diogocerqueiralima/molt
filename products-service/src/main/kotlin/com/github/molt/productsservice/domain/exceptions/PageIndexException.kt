package com.github.molt.productsservice.domain.exceptions

class PageIndexException(

    override val message: String = "The number of page should be bigger than zero"

) : RuntimeException(message)