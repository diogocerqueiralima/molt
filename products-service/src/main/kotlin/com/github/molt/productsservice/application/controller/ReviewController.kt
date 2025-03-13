package com.github.molt.productsservice.application.controller

import com.github.molt.productsservice.application.dto.ApiResponseDto
import com.github.molt.productsservice.application.dto.ReviewCreateDto
import com.github.molt.productsservice.application.dto.ReviewDto
import com.github.molt.productsservice.application.dto.toDto
import com.github.molt.productsservice.domain.model.Review
import com.github.molt.productsservice.domain.services.ReviewService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/reviews")
class ReviewController(

    private val reviewService: ReviewService

) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ApiResponseDto<ReviewDto>> {

        val review = reviewService.getById(id)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Review retrieved successfully",
                    data = review.toDto()
                )
            )
    }

    @PostMapping
    fun create(@AuthenticationPrincipal jwt: Jwt, @RequestBody @Valid dto: ReviewCreateDto): ResponseEntity<ApiResponseDto<ReviewDto>> {

        val userId = jwt.subject.toLong()
        val review = reviewService.create(
            productId = dto.productId,
            comment = dto.comment,
            rating = dto.score,
            userId = userId
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(
                    message = "Review created successfully",
                    data = review.toDto()
                )
            )
    }

}