package com.github.molt.authorizationserver.exceptions

class UserNotFoundException(

    val action: String,
    override val message: String = "User not found"

) : RuntimeException(message)