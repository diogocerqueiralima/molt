package com.github.molt.productsservice.domain.exceptions

class CategoryNotFoundException(

    override val message: String = "Category not found"

) : RuntimeException(message)