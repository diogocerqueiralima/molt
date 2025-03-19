package com.github.molt.productsservice.domain.services

import com.github.molt.productsservice.domain.exceptions.ReviewNotFoundException
import com.github.molt.productsservice.domain.exceptions.ReviewOwnerException
import com.github.molt.productsservice.domain.model.Review
import com.github.molt.productsservice.infrastructure.entities.ReviewEntity
import com.github.molt.productsservice.infrastructure.repositories.ReviewEntityRepository
import com.github.molt.productsservice.infrastructure.services.UserService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ReviewService(

    private val reviewRepository: ReviewEntityRepository,
    private val productService: ProductService,
    private val userService: UserService,
    private val restTemplate: RestTemplate

) {



    fun getById(id: Long): Review {

        val entity = reviewRepository.findById(id).orElseThrow { ReviewNotFoundException() }

        return entity.toDomain(userService.getFullName(entity.userId).fullName)
    }

    fun create(productId: Long, userId: Long, comment: String, rating: Int): Review {

        val product = productService.getById(productId).toEntity()
        val entity = reviewRepository.save(
            ReviewEntity(
                product = product,
                userId = userId,
                comment = comment,
                rating = rating
            )
        )

        return entity.toDomain(userService.getFullName(userId).fullName)
    }

    fun update(id: Long, userId: Long, comment: String?, rating: Int?): Review {

        val entity = getById(id).toEntity()

        if (entity.userId != userId)
            throw ReviewOwnerException()

        val newEntity = reviewRepository.save(
            entity.copy(
                comment = comment ?: entity.comment,
                rating = rating ?: entity.rating
            )
        )

        return newEntity.toDomain(userService.getFullName(userId).fullName)
    }

    fun delete(id: Long, userId: Long) {

        val entity = getById(id).toEntity()

        if (entity.userId != userId)
            throw ReviewOwnerException()

        reviewRepository.delete(entity)
    }

}

fun ReviewEntity.toDomain(userFullName: String) = Review(
    id = this.id,
    userId = this.userId,
    comment = this.comment,
    rating = this.rating,
    userFullName = userFullName
)

fun Review.toEntity() = ReviewEntity(
    id = this.id,
    comment = this.comment,
    rating = this.rating,
    userId = this.userId
)