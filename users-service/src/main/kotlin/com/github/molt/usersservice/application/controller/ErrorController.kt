package com.github.molt.usersservice.application.controller

import com.github.molt.usersservice.application.dto.ApiResponseDto
import com.github.molt.usersservice.exceptions.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleNotFound(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponseDto(message = e.message ?: "")
            )

}