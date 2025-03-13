package com.github.molt.productsservice.domain.services

import com.github.molt.productsservice.infrastructure.entities.CategoryEntity
import com.github.molt.productsservice.domain.exceptions.CategoryNotFoundException
import com.github.molt.productsservice.domain.model.Category
import com.github.molt.productsservice.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(

    private val categoryRepository: CategoryRepository

) {

    fun getById(id: Long): Category {

        val entity = categoryRepository.findById(id).orElseThrow { CategoryNotFoundException() }

        return entity.toDomain()
    }

    fun getAll(): List<Category> =
        categoryRepository.findAll().map { it.toDomain() }

    fun create(name: String, description: String) =
        categoryRepository.save(
            CategoryEntity(name = name, description = description)
        ).toDomain()

    fun update(id: Long, name: String?, description: String?) : Category {

        val entity = getById(id).toEntity()

        return categoryRepository.save(
            entity.copy(
                name = name ?: entity.name,
                description = description ?: entity.description
            )
        ).toDomain()
    }

    fun delete(id: Long) {

        val entity = getById(id).toEntity()

        categoryRepository.delete(entity)
    }

}

fun Category.toEntity() = CategoryEntity(
    id = this.id,
    name = this.name,
    description = this.description
)

fun CategoryEntity.toDomain() = Category(
    id = this.id,
    name = this.name,
    description = this.description
)