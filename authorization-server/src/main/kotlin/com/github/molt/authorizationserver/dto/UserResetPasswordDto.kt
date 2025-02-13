package com.github.molt.authorizationserver.dto

data class UserResetPasswordDto(

    val token: String,

    val password: String = ""

)