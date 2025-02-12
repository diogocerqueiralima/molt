package com.github.diogocerqueiralima.productsservice.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageDto<T>(

    @SerialName("page_size")
    val pageSize: Int,

    @SerialName("total_pages")
    val totalPages: Int,

    val number: Int,

    val content: List<T>

)
