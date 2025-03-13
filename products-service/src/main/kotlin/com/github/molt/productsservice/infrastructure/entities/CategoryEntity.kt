package com.github.molt.productsservice.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @ManyToMany(mappedBy = "categories")
    val products: List<ProductEntity> = emptyList()

)