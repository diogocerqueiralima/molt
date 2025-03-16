package com.github.molt.productsservice.infrastructure.repositories

import com.github.molt.productsservice.infrastructure.entities.CategoryEntity
import com.github.molt.productsservice.infrastructure.entities.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductEntityRepository : JpaRepository<ProductEntity, Long> {

    fun findAllByCategoriesContains(category: CategoryEntity, pageable: Pageable): Page<ProductEntity>

}