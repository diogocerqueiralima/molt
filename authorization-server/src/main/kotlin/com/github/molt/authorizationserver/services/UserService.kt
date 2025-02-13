package com.github.molt.authorizationserver.services

import com.github.molt.authorizationserver.domain.User
import com.github.molt.authorizationserver.exceptions.RegisterPasswordLengthException
import com.github.molt.authorizationserver.exceptions.PasswordMatchException
import com.github.molt.authorizationserver.exceptions.UserNotFoundException
import com.github.molt.authorizationserver.exceptions.UserRegisteredException
import com.github.molt.authorizationserver.producers.UserProducer
import com.github.molt.authorizationserver.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(

    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userProducer: UserProducer

) : UserDetailsService {

    override fun loadUserByUsername(username: String): User =
        userRepository.findByUsernameOrEmail(username, username) ?: throw UsernameNotFoundException("User not found")

    fun register(username: String, email: String, password: String, confirmPassword: String): User {

        if (password.length < 8)
            throw RegisterPasswordLengthException()

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

    fun requestResetPassword(username: String) {

        val user = userRepository.findByUsernameOrEmail(username, username) ?: throw UserNotFoundException("forgot")

        userRepository.save(user.copy(token = UUID.randomUUID()))

        userProducer.publishEmail(user)
    }

    fun resetPassword(token: UUID, password: String) {

        if (password.length < 8 || password.isBlank())
            throw RegisterPasswordLengthException()

        val user = userRepository.findByToken(token) ?: throw UserNotFoundException("reset")

        userRepository.save(user.copy(token = null, password = passwordEncoder.encode(password)))
    }

}