package com.github.diogocerqueiralima.productsservice.controller

import com.github.diogocerqueiralima.productsservice.domain.Category
import com.github.diogocerqueiralima.productsservice.dto.ApiResponseDto
import com.github.diogocerqueiralima.productsservice.dto.CategoryCreateDto
import com.github.diogocerqueiralima.productsservice.dto.CategoryDto
import com.github.diogocerqueiralima.productsservice.dto.CategoryUpdateDto
import com.github.diogocerqueiralima.productsservice.services.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/categories")
class CategoryController(

    private val categoryService: CategoryService

) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ApiResponseDto<CategoryDto>> {

        val category = categoryService.getById(id)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Category retrieved successfully.",
                    data = category.toDto()
                )
            )
    }

    @GetMapping
    fun getAll(): ResponseEntity<ApiResponseDto<List<CategoryDto>>> {

        val categories = categoryService.getAll()

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Categories retrieved successfully.",
                    data = categories.map { it.toDto() }
                )
            )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {

        categoryService.delete(id)

        return ResponseEntity
            .noContent()
            .build()
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: CategoryCreateDto): ResponseEntity<ApiResponseDto<CategoryDto>> {

        val category = categoryService.create(dto.name, dto.description)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(
                    message = "Category created successfully.",
                    data = category.toDto()
                )
            )
    }

    @PutMapping
    fun update(@RequestBody @Valid dto: CategoryUpdateDto): ResponseEntity<ApiResponseDto<CategoryDto>> {

        val category = categoryService.update(dto.id, dto.name, dto.description)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Category updated successfully.",
                    data = category.toDto()
                )
            )
    }

}

fun Category.toDto() = CategoryDto(
    id = this.id,
    name = this.name,
    description = this.description
)