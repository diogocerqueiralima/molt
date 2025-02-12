package com.github.diogocerqueiralima.productsservice.controller

import com.github.diogocerqueiralima.productsservice.dto.ApiResponseDto
import com.github.diogocerqueiralima.productsservice.exceptions.CategoryNotFoundException
import com.github.diogocerqueiralima.productsservice.exceptions.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(CategoryNotFoundException::class, ProductNotFoundException::class)
    fun handleNotFound(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponseDto(
                    message = e.message ?: ""
                )
            )

}