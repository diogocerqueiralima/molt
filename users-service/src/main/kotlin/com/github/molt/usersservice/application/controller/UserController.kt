package com.github.molt.usersservice.application.controller

import com.github.molt.usersservice.application.dto.ApiResponseDto
import com.github.molt.usersservice.application.dto.UserCreateDto
import com.github.molt.usersservice.application.dto.UserDto
import com.github.molt.usersservice.application.dto.toDto
import com.github.molt.usersservice.domain.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(

    private val userService: UserService

) {

    @PostMapping
    fun create(@RequestBody @Valid dto: UserCreateDto): ResponseEntity<ApiResponseDto<UserDto>> {

        println(dto)

        val user = userService.create(dto.email, dto.username, dto.password, dto.firstName, dto.lastName)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(
                    message = "User created successfully",
                    data = user.toDto()
                )
            )
    }

    @GetMapping
    fun getByUsernameOrEmail(@RequestParam username: String? = null, @RequestParam email: String? = null): ResponseEntity<ApiResponseDto<UserDto>> {

        val user = userService.getByUsernameOrEmail(username, email)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "User retrieved successfully",
                    data = user.toDto()
                )
            )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ApiResponseDto<UserDto>> {

        val user = userService.getById(id)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "User retrieved successfully",
                    data = user.toDto()
                )
            )
    }

}