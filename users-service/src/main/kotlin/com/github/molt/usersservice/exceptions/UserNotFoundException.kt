package com.github.molt.usersservice.exceptions

class UserNotFoundException(

    override val message: String = "User not found"

) : RuntimeException(message)