package com.github.molt.productsservice.exceptions

class CategoryNotFoundException(

    override val message: String = "Category not found"

) : RuntimeException(message)