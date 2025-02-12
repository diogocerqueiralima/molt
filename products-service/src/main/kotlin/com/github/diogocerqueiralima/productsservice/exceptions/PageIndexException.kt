package com.github.diogocerqueiralima.productsservice.exceptions

class PageIndexException(

    override val message: String = "The number of page should be bigger than zero"

) : RuntimeException(message)