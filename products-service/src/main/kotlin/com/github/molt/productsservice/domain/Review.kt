package com.github.molt.productsservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "reviews")
data class Review(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    val comment: String,

    val score: Int,

    val userId: Long

)
