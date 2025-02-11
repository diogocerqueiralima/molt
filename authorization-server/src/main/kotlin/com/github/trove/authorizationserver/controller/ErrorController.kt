package com.github.trove.authorizationserver.controller

import com.github.trove.authorizationserver.exceptions.Code
import com.github.trove.authorizationserver.exceptions.RegisterException
import com.github.trove.authorizationserver.exceptions.ResetPasswordLengthException
import com.github.trove.authorizationserver.exceptions.UserNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {

    @ExceptionHandler(RegisterException::class)
    fun handleBadRequest(exception: RegisterException): String =
        "redirect:/auth/register?error=${exception.code}"

    @ExceptionHandler(ResetPasswordLengthException::class)
    fun handle(exception: ResetPasswordLengthException) =
        "redirect:/auth/reset?error=${Code.PASSWORD_LENGTH}"

    @ExceptionHandler(UserNotFoundException::class)
    fun handleNotFound(exception: Exception) =
        "redirect:/auth/login?error"

}