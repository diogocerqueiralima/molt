package com.github.trove.authorizationserver.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val email: String,

    @get:JvmName(name = "username")
    @Column(unique = true, nullable = false)
    val username: String,

    @get:JvmName(name = "password")
    @Column(nullable = false)
    val password: String,

    @Column(unique = true)
    val token: UUID? = null

) : UserDetails {
    
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf()

    override fun getPassword() = password

    override fun getUsername() = username

}