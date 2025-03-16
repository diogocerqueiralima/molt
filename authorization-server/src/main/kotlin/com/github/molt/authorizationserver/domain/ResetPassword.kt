package com.github.molt.authorizationserver.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "reset_password")
data class ResetPassword(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val token: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    val userId: Long

)
