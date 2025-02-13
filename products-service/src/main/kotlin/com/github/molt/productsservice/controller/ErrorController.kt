package com.github.molt.productsservice.controller

import com.github.molt.productsservice.dto.ApiResponseDto
import com.github.molt.productsservice.exceptions.CategoryNotFoundException
import com.github.molt.productsservice.exceptions.PageIndexException
import com.github.molt.productsservice.exceptions.ProductNotFoundException
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

    @ExceptionHandler(PageIndexException::class)
    fun handleBadRequest(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponseDto(
                    message = e.message ?: ""
                )
            )

}