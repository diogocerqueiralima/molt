package com.github.trove.authorizationserver.dto

data class UserResetPasswordDto(

    val token: String,

    val password: String = ""

)