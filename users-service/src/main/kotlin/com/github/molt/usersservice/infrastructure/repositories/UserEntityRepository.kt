package com.github.molt.usersservice.infrastructure.repositories

import com.github.molt.usersservice.infrastructure.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEntityRepository : JpaRepository<UserEntity, Long> {

    fun findUserEntityByUsernameOrEmail(username: String?, email: String?): UserEntity?

}