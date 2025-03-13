package com.github.molt.productsservice.domain.services

import com.github.molt.productsservice.domain.exceptions.ReviewNotFoundException
import com.github.molt.productsservice.domain.model.Review
import com.github.molt.productsservice.infrastructure.entities.ReviewEntity
import com.github.molt.productsservice.infrastructure.repositories.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(

    private val reviewRepository: ReviewRepository,
    private val productService: ProductService

) {

    fun getById(id: Long): Review {

        val entity = reviewRepository.findById(id).orElseThrow { ReviewNotFoundException() }

        return entity.toDomain()
    }

    fun create(productId: Long, userId: Long, comment: String, rating: Int): Review {

        val product = productService.getById(productId).toEntity()

        return reviewRepository.save(
            ReviewEntity(
                product = product,
                userId = userId,
                comment = comment,
                rating = rating
            )
        ).toDomain()
    }

}

fun ReviewEntity.toDomain() = Review(
    id = this.id,
    userId = this.userId,
    comment = this.comment,
    rating = this.rating,
    userFullName = "Diogo Lima"
)