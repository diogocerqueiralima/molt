package com.github.molt.usersservice.domain.exceptions

class UserNotFoundException(

    override val message: String = "User not found"

) : RuntimeException(message)