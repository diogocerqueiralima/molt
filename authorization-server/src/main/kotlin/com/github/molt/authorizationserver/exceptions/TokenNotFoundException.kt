package com.github.molt.authorizationserver.exceptions

class TokenNotFoundException(

    override val message: String = "Token not found"

) : RuntimeException(message)