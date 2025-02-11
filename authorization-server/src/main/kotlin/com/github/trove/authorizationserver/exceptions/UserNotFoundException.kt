package com.github.trove.authorizationserver.exceptions

class UserNotFoundException(

    override val message: String = "User not found"

) : RuntimeException(message)