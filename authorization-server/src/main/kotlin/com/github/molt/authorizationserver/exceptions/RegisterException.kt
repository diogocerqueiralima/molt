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
        INVALID_USERNAME,
        INVALID_NAME

    }

}

class UsernameException : RegisterException(Code.INVALID_USERNAME, "The username is invalid")
class UserAlreadyExistsException : RegisterException(Code.USER_ALREADY_EXISTS, "The username already exists")
class PasswordMatchException : RegisterException(Code.PASSWORD_MATCH, "The password does not match")
class PasswordLengthException : RegisterException(Code.PASSWORD_LENGTH, "The password length is invalid")
class NameException : RegisterException(Code.INVALID_NAME, "The name already exists")
class EmailException : RegisterException(Code.INVALID_EMAIL, "The email is invalid")