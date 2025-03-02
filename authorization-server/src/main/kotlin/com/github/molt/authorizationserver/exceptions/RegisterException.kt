package com.github.molt.authorizationserver.exceptions

open class RegisterException(

    val code: Code,
    override val message: String

) : RuntimeException(message) {

    enum class Code {

        USER_ALREADY_EXISTS,
        PASSWORD_MATCH,
        INVALID_EMAIL,
        PASSWORD_LENGTH,
        INVALID_USERNAME

    }

}

class PasswordMatchException(

    message: String = "Password does not match"

) : RegisterException(Code.PASSWORD_MATCH, message)

class UserAlreadyExistsException(

    message: String = "User already exists"

) : RegisterException(Code.USER_ALREADY_EXISTS, message)

class InvalidEmailException(

    message: String = "Invalid email"

) : RegisterException(Code.INVALID_EMAIL, message)

class PasswordLengthException(

    message: String = "Invalid password"

) : RegisterException(Code.PASSWORD_LENGTH, message)

class InvalidUsernameException(

    message: String = "Invalid username"

) : RegisterException(Code.INVALID_USERNAME, message)