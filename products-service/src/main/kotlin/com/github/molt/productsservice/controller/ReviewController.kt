package com.github.molt.productsservice.controller

import com.github.molt.productsservice.dto.ApiResponseDto
import com.github.molt.productsservice.dto.ReviewCreateDto
import com.github.molt.productsservice.dto.ReviewDto
import com.github.molt.productsservice.dto.toDto
import com.github.molt.productsservice.services.ReviewService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reviews")
class ReviewController(

    private val reviewService: ReviewService

) {

    @PostMapping
    fun create(@AuthenticationPrincipal jwt: Jwt, @RequestBody @Valid dto: ReviewCreateDto): ResponseEntity<ApiResponseDto<ReviewDto>> {

        val userId = jwt.subject.toLong()
        val review = reviewService.create(
            productId = dto.productId,
            comment = dto.comment,
            score = dto.score,
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