package com.github.trove.authorizationserver.exceptions

class UserRegisteredException(

    override val message: String = "User already registered"

) : RegisterException(Code.USER_REGISTERED, message)