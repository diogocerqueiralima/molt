package com.github.diogocerqueiralima.productsservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Category (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @ManyToMany(mappedBy = "categories")
    val products: List<Product> = emptyList()

)