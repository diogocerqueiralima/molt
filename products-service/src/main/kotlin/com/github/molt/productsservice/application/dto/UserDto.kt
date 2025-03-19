package com.github.molt.productsservice.application.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(

    val id: Long,
    val email: String,
    val username: String,
    val password: String,

    @JsonProperty("first_name")
    val firstName: String,

    @JsonProperty("last_name")
    val lastName: String,

    @JsonProperty("full_name")
    val fullName: String

)
