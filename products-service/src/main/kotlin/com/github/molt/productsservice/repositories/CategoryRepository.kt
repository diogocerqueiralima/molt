package com.github.diogocerqueiralima.productsservice.repositories

import com.github.diogocerqueiralima.productsservice.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long>