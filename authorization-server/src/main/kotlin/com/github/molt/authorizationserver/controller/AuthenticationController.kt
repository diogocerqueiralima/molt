package com.github.trove.authorizationserver.controller

import com.github.trove.authorizationserver.dto.UserForgotPasswordDto
import com.github.trove.authorizationserver.dto.UserRegisterDto
import com.github.trove.authorizationserver.dto.UserResetPasswordDto
import com.github.trove.authorizationserver.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.UUID

@Controller
@RequestMapping("/auth")
class AuthenticationController(

    private val userService: UserService

) {

    @GetMapping("/login")
    fun login() = "login"

    @GetMapping("/forgot")
    fun forgot(model: Model): String {

        model.addAttribute("dto", UserForgotPasswordDto())

        return "forgot"
    }

    @PostMapping("/forgot")
    fun forgot(@ModelAttribute dto: UserForgotPasswordDto): String {

        userService.requestResetPassword(dto.usernameOrEmail)

        return "redirect:/auth/forgot?success"
    }

    @GetMapping("/reset")
    fun reset(@RequestParam token: String, model: Model): String {

        model.addAttribute("dto", UserResetPasswordDto(token))

        return "reset"
    }

    @PostMapping("/reset")
    fun reset(@ModelAttribute dto: UserResetPasswordDto): String {

        userService.resetPassword(UUID.fromString(dto.token), dto.password)

        return "redirect:/auth/login"
    }

    @GetMapping("/register")
    fun register(model: Model): String {

        model.addAttribute("user", UserRegisterDto())

        return "register"
    }

    @PostMapping("/register")
    fun register(@ModelAttribute user: UserRegisterDto): String {

        userService.register(user.username, user.email, user.password, user.confirmPassword)

        return "redirect:/auth/login"
    }

}