package com.github.diogocerqueiralima.productsservice.controller

import com.github.diogocerqueiralima.productsservice.domain.Category
import com.github.diogocerqueiralima.productsservice.domain.Product
import com.github.diogocerqueiralima.productsservice.dto.*
import com.github.diogocerqueiralima.productsservice.services.ProductService
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
    fun getPage(@PathVariable number: Int, @RequestParam(required = false) category: Long? = null): ResponseEntity<ApiResponseDto<PageDto<ProductDto>>> {

        val page = productService.getPage(number, category)

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

    @PutMapping
    fun update(@RequestBody @Valid dto: ProductUpdateDto): ResponseEntity<ApiResponseDto<ProductDto>> {

        val product = productService.update(
            id = dto.id,
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

fun Product.toDto() = ProductDto(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    releaseDate = this.releaseDate,
    categories = this.categories.map { it.id }
)