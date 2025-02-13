package com.github.molt.authorizationserver.exceptions

class UserRegisteredException(

    override val message: String = "User already registered"

) : RegisterException(Code.USER_REGISTERED, message)