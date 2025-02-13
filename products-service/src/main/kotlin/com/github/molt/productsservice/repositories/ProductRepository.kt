package com.github.molt.productsservice.repositories

import com.github.molt.productsservice.domain.Category
import com.github.molt.productsservice.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {

    fun findAllByCategoriesContains(category: Category, pageable: Pageable): Page<Product>

}