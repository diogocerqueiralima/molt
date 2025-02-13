package com.github.molt.authorizationserver.exceptions

class ResetPasswordLengthException(

    override val message: String = "The password length is invalid"

) : RuntimeException(message)