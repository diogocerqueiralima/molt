package com.github.diogocerqueiralima.productsservice.exceptions

class CategoryNotFoundException(

    override val message: String = "Category not found"

) : RuntimeException(message)