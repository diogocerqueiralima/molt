package com.github.molt.authorizationserver.exceptions

open class RegisterException(

    val code: Code,
    override val message: String

) : RuntimeException(message)

enum class Code {

    PASSWORD_MATCH,
    PASSWORD_LENGTH,
    USER_REGISTERED

}