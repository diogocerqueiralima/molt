package com.github.trove.authorizationserver.exceptions

class PasswordMatchException(

    override val message: String = "Password does not match"

) : RegisterException(Code.PASSWORD_MATCH, message)