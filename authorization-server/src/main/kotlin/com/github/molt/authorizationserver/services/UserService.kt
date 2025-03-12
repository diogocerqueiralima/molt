package com.github.molt.authorizationserver.services

import com.github.molt.authorizationserver.domain.User
import com.github.molt.authorizationserver.exceptions.*
import com.github.molt.authorizationserver.producers.UserProducer
import com.github.molt.authorizationserver.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Pattern

@Service
class UserService(

    private val userRepository: UserRepository,
    private val userProducer: UserProducer,
    private val passwordEncoder: PasswordEncoder

) : UserDetailsService {

    override fun loadUserByUsername(username: String): User =
        userRepository.findByUsernameOrEmail(username, username) ?: throw UsernameNotFoundException("User $username not found")

    fun register(email: String, username: String, firstName: String, lastName: String, password: String, confirmPassword: String): User {

        if (username.isBlank())
            throw InvalidUsernameException()

        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        val pattern = Pattern.compile(emailRegex)

        if (firstName.isBlank() || lastName.isBlank())
            throw InvalidNameException()

        if (password.trim().length < 8)
            throw PasswordLengthException()

        if (password != confirmPassword)
            throw PasswordMatchException()

        if (!pattern.matcher(email).matches())
            throw InvalidEmailException()

        val user = userRepository.findByUsernameOrEmail(username, email)

        if (user != null)
            throw UserAlreadyExistsException()

        return userRepository.save(
            User(
                email = email,
                username = username,
                password = passwordEncoder.encode(password),
                firstName = firstName,
                lastName = lastName,
            )
        )

    }

    fun forgot(usernameOrEmail: String) {

        val user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) ?: throw UserNotFoundException()

        userProducer.publishEmail(
            userRepository.save(
                user.copy(token = UUID.randomUUID())
            )
        )
    }

    fun reset(token: UUID, password: String) {

        if (password.trim().length < 8)
            throw PasswordLengthException()

        val user = userRepository.findByToken(token) ?: throw UserNotFoundException()

        userRepository.save(
            user.copy(
                token = null,
                password = passwordEncoder.encode(password)
            )
        )
    }

}