package com.github.diogocerqueiralima.productsservice.services

import com.github.diogocerqueiralima.productsservice.domain.Category
import com.github.diogocerqueiralima.productsservice.exceptions.CategoryNotFoundException
import com.github.diogocerqueiralima.productsservice.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(

    private val categoryRepository: CategoryRepository

) {

    fun getById(id: Long): Category =
        categoryRepository.findById(id).orElseThrow { CategoryNotFoundException() }

    fun getAll(): List<Category> =
        categoryRepository.findAll()

    fun create(name: String, description: String) =
        categoryRepository.save(
            Category(name = name, description = description)
        )

    fun update(id: Long, name: String?, description: String?) : Category {

        val category = getById(id)

        return categoryRepository.save(
            category.copy(
                name = name ?: category.name,
                description = description ?: category.description
            )
        )
    }

    fun delete(id: Long) {

        val category = getById(id)

        categoryRepository.delete(category)
    }

}