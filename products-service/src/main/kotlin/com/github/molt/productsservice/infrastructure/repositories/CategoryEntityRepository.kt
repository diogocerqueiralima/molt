package com.github.molt.productsservice.infrastructure.repositories

import com.github.molt.productsservice.infrastructure.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryEntityRepository : JpaRepository<CategoryEntity, Long>