package com.github.molt.productsservice.services

import com.github.molt.productsservice.domain.Product
import com.github.molt.productsservice.exceptions.PageIndexException
import com.github.molt.productsservice.exceptions.ProductNotFoundException
import com.github.molt.productsservice.repositories.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

const val PAGE_SIZE = 10

@Service
class ProductService(

    private val categoryService: CategoryService,
    private val productRepository: ProductRepository

) {

    fun getPage(number: Int, order: Product.Order, categoryId: Long?): Page<Product> {

        if (number <= 0)
            throw PageIndexException()

        val pageRequest = PageRequest.of(number - 1, PAGE_SIZE)
            .withSort(order.direction, order.field)
        val category = categoryId?.let { categoryService.getById(it) }

        return if (category == null)
            productRepository.findAll(pageRequest)
        else
            productRepository.findAllByCategoriesContains(category, pageRequest)
    }

    fun getById(id: Long): Product =
        productRepository.findById(id).orElseThrow { ProductNotFoundException() }

    fun create(name: String, description: String, price: Double, categoriesIds: List<Long>): Product {

        val categories = categoriesIds.map { categoryService.getById(it) }

        return productRepository.save(
            Product(
                name = name,
                description = description,
                price = price,
                categories = categories
            )
        )
    }

    fun update(id: Long, name: String?, description: String?, price: Double?, categoriesIds: List<Long>?): Product {

        val product = getById(id)
        val categories = categoriesIds?.map { categoryService.getById(it) }

        return productRepository.save(
            Product(
                id = product.id,
                name = name ?: product.name,
                description = description ?: product.description,
                price = price ?: product.price,
                categories = categories ?: product.categories
            )
        )
    }

    fun delete(id: Long) {

        val product = getById(id)

        productRepository.delete(product)
    }

}