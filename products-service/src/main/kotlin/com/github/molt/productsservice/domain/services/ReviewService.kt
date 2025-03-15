package com.github.molt.productsservice.domain.services

import com.github.molt.productsservice.domain.exceptions.ReviewNotFoundException
import com.github.molt.productsservice.domain.exceptions.ReviewOwnerException
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

    fun update(id: Long, userId: Long, comment: String?, rating: Int?): Review {

        val entity = getById(id).toEntity()

        if (entity.userId != userId)
            throw ReviewOwnerException()

        return reviewRepository.save(
            entity.copy(
                comment = comment ?: entity.comment,
                rating = rating ?: entity.rating
            )
        ).toDomain()
    }

    fun delete(id: Long, userId: Long) {

        val entity = getById(id).toEntity()

        if (entity.userId != userId)
            throw ReviewOwnerException()

        reviewRepository.delete(entity)
    }

}

fun ReviewEntity.toDomain() = Review(
    id = this.id,
    userId = this.userId,
    comment = this.comment,
    rating = this.rating,
    userFullName = "Diogo Lima"
)

fun Review.toEntity() = ReviewEntity(
    id = this.id,
    comment = this.comment,
    rating = this.rating,
    userId = this.userId
)