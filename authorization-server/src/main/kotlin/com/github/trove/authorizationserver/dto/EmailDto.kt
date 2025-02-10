package com.github.trove.authorizationserver.dto

data class EmailDto(

    val email: String,
    val subject: String,
    val template: String,
    val variables: Map<String, String>

)