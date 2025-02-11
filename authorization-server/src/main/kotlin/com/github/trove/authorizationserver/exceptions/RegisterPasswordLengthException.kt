package com.github.trove.authorizationserver.exceptions

class RegisterPasswordLengthException(

    override val message: String = "The password length is invalid"

) : RegisterException(Code.PASSWORD_LENGTH, message)