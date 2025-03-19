package com.github.molt.productsservice.domain.services

import com.github.molt.productsservice.infrastructure.entities.ProductEntity
import com.github.molt.productsservice.domain.exceptions.PageIndexException
import com.github.molt.productsservice.domain.exceptions.ProductNotFoundException
import com.github.molt.productsservice.domain.model.Product
import com.github.molt.productsservice.domain.model.Review
import com.github.molt.productsservice.infrastructure.repositories.ProductEntityRepository
import com.github.molt.productsservice.infrastructure.services.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

const val PAGE_SIZE = 10

@Service
class ProductService(

    private val categoryService: CategoryService,
    private val productRepository: ProductEntityRepository,
    private val userService: UserService

) {

    fun getPage(number: Int, order: Product.Order, categoryId: Long?): Page<Product> {

        if (number <= 0)
            throw PageIndexException()

        val pageRequest = PageRequest.of(number - 1, PAGE_SIZE)
            .withSort(order.direction, order.field)
        val categoryEntity = categoryId?.let { categoryService.getById(it) }?.toEntity()

        return (
                if (categoryEntity == null)
                    productRepository.findAll(pageRequest)
                else
                    productRepository.findAllByCategoriesContains(categoryEntity, pageRequest)
                ).map { it.toDomain(it.reviews.map { reviewEntity -> reviewEntity.toDomain(userService.getFullName(reviewEntity.userId).fullName) }) }
    }

    fun getById(id: Long): Product {

        val entity = productRepository.findById(id).orElseThrow { ProductNotFoundException() }
        val reviews = entity.reviews.map { reviewEntity -> reviewEntity.toDomain(userService.getFullName(reviewEntity.userId).fullName) }

        return entity.toDomain(reviews)
    }

    fun create(name: String, description: String, price: Double, categoriesIds: List<Long>): Product {

        val categories = categoriesIds.map { categoryService.getById(it) }.map { it.toEntity() }

        return productRepository.save(
            ProductEntity(
                name = name,
                description = description,
                price = price,
                categories = categories
            )
        ).toDomain(emptyList())
    }

    fun update(id: Long, name: String?, description: String?, price: Double?, categoriesIds: List<Long>?): Product {

        val entity = getById(id).toEntity()
        val categories = categoriesIds?.map { categoryService.getById(it) }?.map { it.toEntity() }

        return productRepository.save(
            entity.copy(
                name = name ?: entity.name,
                description = description ?: entity.description,
                price = price ?: entity.price,
                categories = categories ?: entity.categories
            )
        ).toDomain(entity.reviews.map { reviewEntity -> reviewEntity.toDomain(userService.getFullName(reviewEntity.userId).fullName) })
    }

    fun delete(id: Long) {

        val entity = getById(id).toEntity()

        productRepository.delete(entity)
    }

}

fun Product.toEntity() = ProductEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    releaseDate = this.releaseDate,
    categories = this.categories.map { it.toEntity() }
)

fun ProductEntity.toDomain(reviews: List<Review>) = Product(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    releaseDate = this.releaseDate,
    categories = this.categories.map { it.toDomain() },
    reviews = reviews
)