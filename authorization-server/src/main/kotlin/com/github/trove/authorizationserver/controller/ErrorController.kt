package com.github.trove.authorizationserver.controller

import com.github.trove.authorizationserver.exceptions.RegisterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {

    @ExceptionHandler(RegisterException::class)
    fun handleBadRequest(exception: RegisterException): String =
        "redirect:/auth/register?error=${exception.code}"

}