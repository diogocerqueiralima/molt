package com.github.molt.productsservice.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "reviews")
data class ReviewEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: ProductEntity,

    val comment: String,

    val rating: Int,

    val userId: Long

)