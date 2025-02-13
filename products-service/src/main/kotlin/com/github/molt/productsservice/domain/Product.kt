package com.github.molt.productsservice.domain

import jakarta.persistence.*
import org.springframework.data.domain.Sort
import java.time.LocalDateTime

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

    @Column(nullable = false)
    val releaseDate: LocalDateTime = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: List<Category> = emptyList()

) {

    enum class Order(
        val field: String,
        val direction: Sort.Direction
    ) {

        MOST_RECENT("releaseDate", Sort.Direction.DESC),
        OLDEST("releaseDate", Sort.Direction.ASC),
        LOWEST_PRICE("price", Sort.Direction.ASC),
        HIGHEST_PRICE("price", Sort.Direction.DESC),

    }

}