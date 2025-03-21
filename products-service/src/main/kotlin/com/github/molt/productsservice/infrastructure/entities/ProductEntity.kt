package com.github.molt.productsservice.infrastructure.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class ProductEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(columnDefinition = "text")
    val description: String,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false)
    val releaseDate: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<CategoryEntity> = emptyList(),

    @OneToMany(mappedBy = "product")
    val reviews: List<ReviewEntity> = emptyList()

)