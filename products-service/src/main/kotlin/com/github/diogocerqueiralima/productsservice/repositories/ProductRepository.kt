package com.github.diogocerqueiralima.productsservice.repositories

import com.github.diogocerqueiralima.productsservice.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>