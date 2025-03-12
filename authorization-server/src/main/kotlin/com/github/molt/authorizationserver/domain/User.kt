package com.github.molt.authorizationserver.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
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

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @get:JvmName(name = "password")
    @Column(nullable = false)
    val password: String,

    @Column(unique = true)
    val token: UUID? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val roles: List<Role> = listOf(Role.USER)

) : UserDetails {

    val name
        get() = "$firstName $lastName"

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        roles.map { SimpleGrantedAuthority("ROLE_${it.name}") }.toMutableList()

    override fun getPassword() = password

    override fun getUsername() = username

}