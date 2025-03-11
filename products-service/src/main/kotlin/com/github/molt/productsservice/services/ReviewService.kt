package com.github.molt.productsservice.services

import com.github.molt.productsservice.domain.Review
import com.github.molt.productsservice.repositories.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(

    private val reviewRepository: ReviewRepository,
    private val productService: ProductService

) {

    fun create(productId: Long, userId: Long, comment: String, score: Int): Review {

        val product = productService.getById(productId)

        return reviewRepository.save(
            Review(
                product = product,
                userId = userId,
                comment = comment,
                score = score
            )
        )
    }

}