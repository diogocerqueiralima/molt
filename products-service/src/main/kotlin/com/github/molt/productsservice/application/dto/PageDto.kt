package com.github.molt.productsservice.application.dto

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
