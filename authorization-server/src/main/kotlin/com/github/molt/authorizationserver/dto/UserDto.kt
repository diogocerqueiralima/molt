package com.github.molt.authorizationserver.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(

    val id: Long,
    val email: String,
    val username: String,
    val password: String,

    @SerialName("first_name")
    val first_name: String,

    @SerialName("last_name")
    val last_name: String,

    @SerialName("full_name")
    val full_name: String

)
