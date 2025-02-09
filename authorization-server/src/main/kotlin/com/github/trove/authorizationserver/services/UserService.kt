package com.github.trove.authorizationserver.services

import com.github.trove.authorizationserver.domain.User
import com.github.trove.authorizationserver.exceptions.PasswordLengthException
import com.github.trove.authorizationserver.exceptions.PasswordMatchException
import com.github.trove.authorizationserver.exceptions.UserRegisteredException
import com.github.trove.authorizationserver.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(

    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder

) : UserDetailsService {

    override fun loadUserByUsername(username: String): User =
        userRepository.findByUsernameOrEmail(username, username) ?: throw UsernameNotFoundException("User not found")

    fun register(username: String, email: String, password: String, confirmPassword: String): User {

        if (password.length < 8)
            throw PasswordLengthException()

        if (password != confirmPassword)
            throw PasswordMatchException()

        val user = userRepository.findByUsernameOrEmail(username, email)

        if (user != null)
            throw UserRegisteredException()

        return userRepository.save(
            User(
                username = username,
                email = email,
                password = passwordEncoder.encode(password)
            )
        )

    }

}