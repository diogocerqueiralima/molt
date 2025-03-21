package com.github.molt.usersservice.domain.services

import com.github.molt.usersservice.domain.exceptions.UserNotFoundException
import com.github.molt.usersservice.domain.model.User
import com.github.molt.usersservice.infrastructure.entities.UserEntity
import com.github.molt.usersservice.infrastructure.repositories.UserEntityRepository
import org.springframework.stereotype.Service

@Service
class UserService(

    private val userEntityRepository: UserEntityRepository

) {

    fun create(email: String, username: String, password: String, firstName: String, lastName: String): User {

        return userEntityRepository.save(
            UserEntity(
                email = email,
                username = username,
                password = password,
                firstName = firstName,
                lastName = lastName
            )
        ).toDomain()
    }

    fun updatePassword(id: Long, password: String): User {

        val entity = userEntityRepository.findById(id).orElseThrow { UserNotFoundException() }

        return userEntityRepository.save(
            entity.copy(password = password)
        ).toDomain()
    }

    fun getByUsernameOrEmail(username: String?, email: String?): User {

        val entity = userEntityRepository.findUserEntityByUsernameOrEmail(username, email) ?: throw UserNotFoundException()

        return entity.toDomain()
    }

    fun getById(id: Long): User {

        val entity = userEntityRepository.findById(id).orElseThrow { UserNotFoundException() }

        return entity.toDomain()
    }

    private fun UserEntity.toDomain() = User(
        id = this.id,
        username = this.username,
        email = this.email,
        password = this.password,
        firstName = this.firstName,
        lastName = this.lastName
    )

}