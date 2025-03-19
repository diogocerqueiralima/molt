package com.github.molt.authorizationserver.services

import com.github.molt.authorizationserver.domain.ResetPassword
import com.github.molt.authorizationserver.dto.*
import com.github.molt.authorizationserver.exceptions.*
import com.github.molt.authorizationserver.producers.ResetPasswordProducer
import com.github.molt.authorizationserver.repositories.ResetPasswordRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.util.*
import java.util.regex.Pattern

@Service
class UserService(

    private val restTemplate: RestTemplate,
    private val resetPasswordRepository: ResetPasswordRepository,
    private val resetPasswordProducer: ResetPasswordProducer,
    private val passwordEncoder: PasswordEncoder

) : UserDetailsService {

    private val userServiceUri = "http://users-service:8080/api/v1/users"

    override fun loadUserByUsername(username: String): UserDetails {

        val responseEntity = restTemplate.getForEntity("$userServiceUri?username=$username", UserApiResponseDto::class.java)
        val apiResponse = responseEntity.body ?: throw RuntimeException("Body is missing")
        val user = apiResponse.data

        return object : UserDetails {
            override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
                mutableListOf()

            override fun getPassword() =
                user.password

            override fun getUsername() =
                user.username

        }

    }

    fun register(username: String, email: String, password: String, confirmPassword: String, firstName: String, lastName: String) {

        if (username.isBlank())
            throw UsernameException()

        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        val pattern = Pattern.compile(emailRegex)

        if (firstName.isBlank() || lastName.isBlank())
            throw NameException()

        if (password.trim().length < 8)
            throw PasswordLengthException()

        if (password != confirmPassword)
            throw PasswordMatchException()

        if (!pattern.matcher(email).matches())
            throw EmailException()

        try {
            restTemplate.exchange("$userServiceUri?username=$username&email=$email", HttpMethod.GET, null, ApiResponseDto::class.java)
            throw UserAlreadyExistsException()
        }catch (_: RestClientException) { }

        val entity = HttpEntity<UserCreateDto>(
            UserCreateDto(username, email, passwordEncoder.encode(password), firstName, lastName)
        )

        val responseEntity = restTemplate.postForEntity(userServiceUri, entity, ApiResponseDto::class.java)

        if (responseEntity.statusCode != HttpStatus.CREATED)
            throw RuntimeException("Something went wrong with the request")
    }

    fun forgot(usernameOrEmail: String) {

        val responseEntity = restTemplate.getForEntity("$userServiceUri?username=$usernameOrEmail&email=$usernameOrEmail", UserApiResponseDto::class.java)
        val apiResponse = responseEntity.body ?: throw RuntimeException("Body is missing")
        val user = apiResponse.data
        val resetPassword = resetPasswordRepository.findByUserId(user.id) ?: resetPasswordRepository.save(ResetPassword(userId = user.id))

        resetPasswordProducer.publishEmail(
            resetPassword,
            user.email,
            user.
            fullName
        )
    }

    fun reset(token: UUID, password: String) {

        if (password.trim().length < 8)
            throw PasswordLengthException()

        val resetPassword = resetPasswordRepository.findByToken(token) ?: throw TokenNotFoundException()

        resetPasswordRepository.delete(resetPassword)

        val httpEntity = HttpEntity(mapOf("password" to passwordEncoder.encode(password)))
        restTemplate.exchange("$userServiceUri/${resetPassword.userId}", HttpMethod.PUT, httpEntity, ApiResponseDto::class.java)
    }

}