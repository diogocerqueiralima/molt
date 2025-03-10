package com.github.molt.authorizationserver.exceptions

class UserNotFoundException(

    override val message: String = "User not found"

) : RuntimeException(message)