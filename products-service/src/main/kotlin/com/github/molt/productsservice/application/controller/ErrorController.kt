package com.github.molt.productsservice.application.controller

import com.github.molt.productsservice.application.dto.ApiResponseDto
import com.github.molt.productsservice.domain.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(CategoryNotFoundException::class, ProductNotFoundException::class, ReviewNotFoundException::class)
    fun handleNotFound(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponseDto(
                    message = e.message ?: ""
                )
            )

    @ExceptionHandler(PageIndexException::class)
    fun handleBadRequest(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponseDto(
                    message = e.message ?: ""
                )
            )

    @ExceptionHandler(ReviewOwnerException::class)
    fun handleForbidden(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(
                ApiResponseDto(
                    message = e.message ?: ""
                )
            )

}