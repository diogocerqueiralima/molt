package com.github.molt.productsservice.dto

import com.github.molt.productsservice.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ProductDto(

    val id: Long,

    val name: String,

    val description: String,

    val price: Double,

    @SerialName("release_date")
    @Serializable(with = LocalDateTimeSerializer::class)
    val releaseDate: LocalDateTime,

    val categories: List<Long>

)
