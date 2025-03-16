package com.github.molt.authorizationserver.repositories

import com.github.molt.authorizationserver.domain.ResetPassword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ResetPasswordRepository : JpaRepository<ResetPassword, Long> {

    fun findByToken(token: UUID): ResetPassword?

}