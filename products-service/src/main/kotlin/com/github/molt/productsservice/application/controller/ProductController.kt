package com.github.molt.productsservice.application.controller

import com.github.molt.productsservice.application.dto.*
import com.github.molt.productsservice.domain.model.Product
import com.github.molt.productsservice.infrastructure.entities.ProductEntity
import com.github.molt.productsservice.domain.services.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(

    private val productService: ProductService

) {

    @GetMapping("/page/{number}")
    fun getPage(
        @PathVariable number: Int,
        @RequestParam(required = false) category: Long? = null,
        @RequestParam(required = false) order: Product.Order = Product.Order.MOST_RECENT
    ): ResponseEntity<ApiResponseDto<PageDto<ProductDto>>> {

        val page = productService.getPage(number, order, category)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Page of products retrieved successfully.",
                    data = PageDto(
                        totalPages = page.totalPages,
                        pageSize = page.size,
                        number = page.number + 1,
                        content = page.content.map { it.toDto() }
                    )
                )
            )

    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.getById(id)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Product retrieved successfully.",
                    data = product.toDto()
                )
            )
    }

    @PostMapping
    fun create(@RequestBody @Valid dto: ProductCreateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.create(
            name = dto.name,
            description = dto.description,
            price = dto.price,
            categoriesIds = dto.categories
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(
                    message = "Product created successfully.",
                    data = product.toDto()
                )
            )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: ProductUpdateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.update(
            id = id,
            name = dto.name,
            description = dto.description,
            price = dto.price,
            categoriesIds = dto.categories
        )

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Product updated successfully.",
                    data = product.toDto()
                )
            )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ApiResponseDto<Unit>> {

        productService.delete(id)

        return ResponseEntity
            .ok(
                ApiResponseDto(
                    message = "Product deleted successfully."
                )
            )
    }

}