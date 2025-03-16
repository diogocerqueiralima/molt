package com.github.molt.usersservice.domain.model

data class User(

    val id: Long,
    val email: String,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String

) {

    val fullName = "$firstName $lastName"

}