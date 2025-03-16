package com.github.molt.usersservice.application.dto

import com.github.molt.usersservice.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(

    val id: Long,
    val email: String,
    val username: String,
    val password: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("full_name")
    val fullName: String

)

fun User.toDto() = UserDto(
    id = this.id,
    email = this.email,
    username = this.username,
    password = this.password,
    firstName = this.firstName,
    lastName = this.lastName,
    fullName = this.fullName
)
