package com.github.diogocerqueiralima.productsservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(columnDefinition = "text")
    val description: String,

    @Column(nullable = false)
    val price: Double,

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<Category> = emptyList()

)